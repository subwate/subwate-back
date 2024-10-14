package com.project.subwate_backend.application.station.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.application.station.service.StationService;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.infrastructure.station.repository.StationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public ResponseDto<StationDto> getStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);

        if (station == null) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "지하철 역 조회에 실패했습니다.", null);
        }

        StationDto stationDto = station.toStationDto();

        return ResponseDto.of(HttpStatus.OK, "지하철 역 조회에 성공했습니다.", stationDto);
    }

    @Override
    public ResponseDto<List<StationDto>> getNearestStation(double latitude, double longitude) {
        List<Station> stationList = stationRepository.getNearestStation(latitude, longitude);

        if (stationList.isEmpty()) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "가장 가까운 지하철 역 조회에 실패했습니다.", null);
        }

        List<StationDto> stationDtoList = stationList.stream()
                .map(Station::toStationDto)
                .collect(Collectors.toList());

        return ResponseDto.of(HttpStatus.OK, "가장 가까운 지하철 역 조회에 성공했습니다.", stationDtoList);
    }
}