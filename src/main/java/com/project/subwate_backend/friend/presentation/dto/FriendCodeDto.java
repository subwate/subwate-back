package com.project.subwate_backend.friend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class FriendCodeDto {
    
    private String friendCode;

    private LocalDateTime expirationTime;
}
