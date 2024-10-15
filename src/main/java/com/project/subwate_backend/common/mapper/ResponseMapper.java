package com.project.subwate_backend.common.mapper;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.domain.subwayline.entity.SubwayLine;
import com.project.subwate_backend.domain.user.entity.User;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    UserLoginDto toUserLoginDto(User user);

    UserResponseDto toUserResponseDto(User user);

    StationDto toStationDto(Station station);

    SubwayLineDto toSubwayLineDto(SubwayLine subwayLine);
}
