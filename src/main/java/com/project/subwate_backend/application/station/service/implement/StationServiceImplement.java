package com.project.subwate_backend.application.station.service.implement;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.project.subwate_backend.application.station.service.StationService;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.infrastructure.station.repository.StationRepository;
import com.project.subwate_backend.presentation.subway.dto.StationWithSubwayLinesDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StationServiceImplement implements StationService {

    private final StationRepository stationRepository;

    @Override
    public StationWithSubwayLinesDto findNearestStation(double latitude, double longitude) {

        Station station = stationRepository.findNearestStation(latitude, longitude);
        String stationName = station.getName();

        List<Station> stations = stationRepository.findByName(stationName);
        List<Long> subwayLineIdList = stations.stream()
                .map(Station::getSubwayLineId)
                .collect(Collectors.toList());

        StationWithSubwayLinesDto nearestStation = new StationWithSubwayLinesDto(
                stationName, subwayLineIdList, station.getLatitude(), station.getLongitude());

        return nearestStation;
    }
}