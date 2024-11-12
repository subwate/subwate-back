package com.project.subwate_backend.presentation.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShareLocationDto {

    private Long ShareLocationId;

    private Long requesterId;

    private Long receiverId;

    private LocalDateTime expirationTime;

    private boolean isActive;
}
