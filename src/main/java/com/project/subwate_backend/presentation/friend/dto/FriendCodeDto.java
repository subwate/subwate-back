package com.project.subwate_backend.presentation.friend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class FriendCodeDto {
    
    private String friendCode;

    private LocalDateTime expirationTime;
}
