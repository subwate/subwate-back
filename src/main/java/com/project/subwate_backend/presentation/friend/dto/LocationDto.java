package com.project.subwate_backend.presentation.friend.dto;

import com.project.subwate_backend.presentation.subway.dto.SubwayDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationDto {
    
    private Long userId;

    private SubwayDto subway;
}
