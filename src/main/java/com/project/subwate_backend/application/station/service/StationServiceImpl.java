package com.project.subwate_backend.application.station.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.infrastructure.station.repository.StationRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StationServiceImpl implements StationService {

    StationRepository stationRepository;
    ResponseMapper responseMapper;

    public ResponseDto<StationDto> getStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);

        if (station == null) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "지하철 역 조회에 실패했습니다.", null);
        }

        StationDto stationDto = responseMapper.toStationDto(station);

        return ResponseDto.of(HttpStatus.OK, "지하철 역 조회에 성공했습니다.", stationDto);
    }

    @Override
    public ResponseDto<List<StationDto>> getNearestStation(double latitude, double longitude) {
        List<Station> stationList = stationRepository.getNearestStation(latitude, longitude);

        if (stationList.isEmpty()) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "가장 가까운 지하철 역 조회에 실패했습니다.", null);
        }

        List<StationDto> stationDtoList = stationList.stream()
                .map(responseMapper::toStationDto)
                .collect(Collectors.toList());

        return ResponseDto.of(HttpStatus.OK, "가장 가까운 지하철 역 조회에 성공했습니다.", stationDtoList);
    }
}