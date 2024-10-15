package com.project.subwate_backend.application.station.service;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import java.util.List;

public interface StationService {

    ResponseDto<StationDto> getStation(Long id);

    ResponseDto<List<StationDto>> getNearestStation(double latitude, double longitude);

}