package com.project.subwate_backend.infrastructure.kakao.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class KakaoApiService {
    @Value("${kakao.client_id}")
    String client_id;

    @Value("${kakao.redirect_uri}")
    String redirect_uri;

    public String getAccessToken(String code) {
        log.info("Get access token"+client_id+" "+redirect_uri);
        String accessToken = null;
        String KakaoUrl = "https://kakao.com/oauth/access_token";

        try {
            HttpClient client = HttpClient.newHttpClient();
            String body = String.format(
                    "grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                    client_id, redirect_uri, code);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(KakaoUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                accessToken = jsonNode.get("access_token").asText();
            } else {
                log.error("Failed to get access token. Response code: {}", response.statusCode());
            }

        } catch (Exception e) {
            log.error("Error occurred while fetching access token", e);
        }

        return accessToken;
    }
}
