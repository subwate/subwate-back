package com.project.subwate_backend.presentation.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendDto {
    
    private Long friendId;
    
    private String nickName;

    private String imageUrl;
}
