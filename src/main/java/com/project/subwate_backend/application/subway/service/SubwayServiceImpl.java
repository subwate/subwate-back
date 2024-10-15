package com.project.subwate_backend.application.subway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.common.utils.JsonConverter;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.infrastructure.api.ApiClient;
import com.project.subwate_backend.infrastructure.station.repository.StationRepository;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubwayServiceImpl implements SubwayService {

    private final ApiClient apiClient;
    private final JsonConverter jsonConverter;
    private final StationRepository stationRepository;

    @Override
    public ResponseDto<List<SubwayDto>> getSubwayByStation(Long stationId) {

        Station station = stationRepository.findById(stationId).orElse(null);

        if (station == null) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "해당 지하철 역이 존재하지 않습니다.", null);
        }

        try {
            String stationName = station.getName();
            List<Map<String, Object>> arrivalList = apiClient.getSubwayArrivalData(stationName);
            List<SubwayDto> subwayDtoList = new ArrayList<>();

            for (int i = 0; i < arrivalList.size(); i++) {
                Map<String, Object> arrivalData = arrivalList.get(i);

                String currentStatnIdStr = (String) arrivalData.get("statnId");
                String nextStationIdStr = (String) arrivalData.get("statnTid");

                if (nextStationIdStr.equals(String.valueOf(stationId)) || currentStatnIdStr.equals(String.valueOf(stationId))) {
                    SubwayDto subwayDto = jsonConverter.convertToSubwayDto(arrivalData);
                    subwayDtoList.add(subwayDto);
                }
            }

            return ResponseDto.of(HttpStatus.OK, "지하철 정보 조회 성공", subwayDtoList);

        } catch (Exception e) {
            return ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, "지하철 정보 조회 실패: " + e.getMessage(), null);
        }
    }
}
