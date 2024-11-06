package com.project.subwate_backend.infrastructure.google.service;

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
public class GoogleApiService {

    @Value("${google.client_id}")
    String clientId;

    @Value("${google.client_secret}")
    String clientSecret;

    @Value("${google.redirect_url}")
    String redirectUri;

    static final ObjectMapper objectMapper = new ObjectMapper();

    public String getAccessToken(String code) {
        log.info("Get access token: {} {}", clientId, redirectUri);
        String googleUrl = "https://oauth2.googleapis.com/token";
        String body = String.format(
                "grant_type=authorization_code&client_id=%s&client_secret=%s&redirect_uri=%s&code=%s",
                clientId, clientSecret, redirectUri, code);

        String response = sendPostRequest(googleUrl, body, null);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new OauthException(OAUTH_ACCESS_TOKEN_GET_FAILED, "access token parsing error", e.getCause());
        }
    }

    public UserLoginDto getUserInfo(String accessToken) {
        UserLoginDto userLoginDto = new UserLoginDto();
        String reqUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        log.info(accessToken);

        String response = sendPostRequest(reqUrl, null, "Bearer " + accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);

            String email = jsonNode.path("email").asText("");
            String name = jsonNode.path("name").asText("");

            if (name.isBlank() || email.isBlank()) {
                throw new OauthException(OAUTH_USER_INFO_GET_FAILED, "value is blank name: " + name + " email: " + email);
            }

            userLoginDto.setEmail(email);
            userLoginDto.setNickname(name); // Google에서는 name을 nickname으로 사용

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
                    .header("Content-Type", "application/x-www-form-urlencoded");

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

            log.info("[GoogleApi] responseCode: {}", response.statusCode());
            log.info("[GoogleApi] response: {}", response.body());

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
