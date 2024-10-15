package com.project.subwate_backend.application.subway.service;

import java.util.List;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;

public interface SubwayService {

    public ResponseDto<List<SubwayDto>> getSubwayByStation(Long stationId);

}