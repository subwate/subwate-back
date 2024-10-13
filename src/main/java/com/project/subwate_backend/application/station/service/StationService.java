package com.project.subwate_backend.application.station.service;

import com.project.subwate_backend.presentation.subway.dto.StationWithSubwayLinesDto;

public interface StationService {
    
    StationWithSubwayLinesDto findNearestStation(double latitude, double longitude);
}