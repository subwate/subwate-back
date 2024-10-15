package com.project.subwate_backend.infrastructure.kakao.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.subwate_backend.infrastructure.exception.OauthException;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.project.subwate_backend.common.ResponseCode.*;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoApiService {
    @Value("${kakao.client_id}")
    String clientId;

    @Value("${kakao.redirect_uri}")
    String redirectUri;

    static final ObjectMapper objectMapper = new ObjectMapper();

    public String getAccessToken(String code) {
        log.info("Get access token: {} {}", clientId, redirectUri);
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";
        String body = String.format(
                "grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                clientId, redirectUri, code);

        String response = sendPostRequest(kakaoUrl, body, null);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new OauthException(OAUTH_ACCESS_TOKEN_GET_FAILED, "access token parsing error", e.getCause());
        }
    }

    public UserLoginDto getUserInfo(String accessToken) {
        UserLoginDto userLoginDto = new UserLoginDto();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        log.info(accessToken);

        String response = sendPostRequest(reqUrl, null, "Bearer " + accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode properties = jsonNode.path("properties");
            JsonNode kakaoAccount = jsonNode.path("kakao_account");

            String nickname = properties.path("nickname").asText("");
            String email = kakaoAccount.path("email").asText("");

            if (nickname.isBlank() || email.isBlank()) {
                throw new OauthException(OAUTH_USER_INFO_GET_FAILED, "value is blank nickname: " + nickname + " email: " + email);
            }

            userLoginDto.setEmail(email);
            userLoginDto.setNickname(nickname);

            return userLoginDto;
        } catch (Exception e) {
            throw new OauthException(OAUTH_USER_INFO_GET_FAILED, e.getMessage());
        }
    }

    private String sendPostRequest(String url, String body, String authorization) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            if (authorization != null) {
                requestBuilder.header("Authorization", authorization);
            }

            if (body != null) {
                requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
            } else {
                requestBuilder.POST(HttpRequest.BodyPublishers.noBody());
            }

            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("[KakaoApi] responseCode: {}", response.statusCode());
            log.info("[KakaoApi] response: {}", response.body());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                if (response.body() == null) {
                    throw new OauthException(OAUTH_DATA_GET_FAILED, "response is null");
                }

                return response.body();
            } else {
                throw new OauthException(OAUTH_DATA_GET_FAILED, "return status code : " + response.statusCode());
            }

        } catch (Exception e) {
            log.error("Error occurred while sending request to {}: {}", url, e.getMessage());
            Thread.currentThread().interrupt();
            throw new OauthException(OAUTH_DATA_GET_FAILED, "응답에 오류가 발생했습니다: " + e.getMessage());
        }
    }
}