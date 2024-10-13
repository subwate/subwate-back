package com.project.subwate_backend.presentation.subway.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StationWithSubwayLinesDto {

    private String stationName;
    private List<Long> subwayLineIdList;
    private double latitude;
    private double longitude;
}