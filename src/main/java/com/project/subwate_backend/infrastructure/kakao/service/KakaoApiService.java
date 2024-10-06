package com.project.subwate_backend.infrastructure.kakao.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.subwate_backend.infrastructure.exception.OauthException;
import com.project.subwate_backend.presentation.user.dto.response.UserInfoDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
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

        if (response == null) {
            throw new OauthException("Kakao Login access token을 받아오는데 실패했습니다.", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            log.error("Error parsing access token response", e);
            throw new OauthException("Kakao Login access token을 파싱하는 과정에 문제가 발생했습니다.", e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserInfoDto getUserInfo(String accessToken) {
        UserInfoDto userInfoDto = new UserInfoDto();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        log.info(accessToken);

        String response = sendPostRequest(reqUrl, null, "Bearer " + accessToken);

        if (response == null) {
            throw new OauthException("Access token을 사용하여 Kakao 사용자 정보를 받아오는데 실패했습니다.", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode properties = jsonNode.path("properties");
            JsonNode kakaoAccount = jsonNode.path("kakao_account");

            String nickname = properties.path("nickname").asText("");
            String email = kakaoAccount.path("email").asText("");

            userInfoDto.setEmail(email);
            userInfoDto.setNickname(nickname);

            return userInfoDto;
        } catch (Exception e) {
            log.error("Error occurred while sending request to {}", e.getMessage());
            throw new OauthException("Kakao Login 사용자 정보를 파싱하는 과정에 문제가 발생했습니다.", e.getCause(), HttpStatus.BAD_REQUEST);
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
                return response.body();
            } else {
                log.error("Failed to send request. Response code: {}", response.statusCode());
                throw new OauthException("Kakao Login 처리 과정에서 문제가 발생했습니다.", HttpStatus.valueOf(response.statusCode()));
            }
        } catch (Exception e) {
            log.error("Error occurred while sending request to {}: {}", url, e.getMessage());
            Thread.currentThread().interrupt();
            throw new OauthException("Kakao Login 처리 과정에서 문제가 발생했습니다.", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}