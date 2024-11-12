package com.project.subwate_backend.common.mapper;

import com.project.subwate_backend.user.domain.entity.User;
import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;
import com.project.subwate_backend.user.presentation.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    UserLoginDto toUserLoginDto(User user);

    UserResponseDto toUserResponseDto(User user);
}
