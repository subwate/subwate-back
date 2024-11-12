package com.project.subwate_backend.friend.presentation.dto;

import com.project.subwate_backend.subway.presentation.dto.SubwayDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationDto {
    
    private Long userId;

    private SubwayDto subway;
}
