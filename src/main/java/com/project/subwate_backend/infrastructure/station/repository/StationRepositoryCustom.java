package com.project.subwate_backend.infrastructure.station.repository;

import com.project.subwate_backend.domain.station.entity.Station;

import java.util.List;

public interface StationRepositoryCustom {
    List<Station> getNearestStation(double latitude, double longitude);
}
