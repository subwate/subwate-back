package com.project.subwate_backend.infrastructure.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.seoul_data_api_key}")
    private String seoulDataApiKey;

    public List<Map<String, Object>> getSubwayArrivalData(String stationName) throws Exception {

        String apiUrl = UriComponentsBuilder.fromHttpUrl("http://swopenapi.seoul.go.kr/api/subway")
                .pathSegment(seoulDataApiKey)
                .pathSegment("json")
                .pathSegment("realtimeStationArrival")
                .pathSegment("0")
                .pathSegment("10")
                .pathSegment(stationName)
                .build(false)
                .toUriString();

        JSONObject jsonObject = restTemplate.getForObject(apiUrl, JSONObject.class);

        if (jsonObject == null || !jsonObject.containsKey("realtimeArrivalList")) {
            throw new Exception("Invalid response from the API");
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> arrivalList = (List<Map<String, Object>>) jsonObject.get("realtimeArrivalList");

        if (arrivalList.isEmpty()) {
            throw new Exception("No arrival data found for the station: " + stationName);
        }

        return arrivalList;
    }

}
