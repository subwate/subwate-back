package com.project.subwate_backend.domain.user.entity;

import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subwate_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String email;
    @Column
    String name;
    @Column
    String nickname;
    @Column
    String oauthDomain;
    @Column
    Date createdAt;


    @Builder
    public User(String email, String name, String nickname, String oauthDomain) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.oauthDomain = oauthDomain;
    }

    public static User from(UserLoginDto userLoginDto) {
        return User.builder()
                .email(userLoginDto.getEmail())
                .name(userLoginDto.getName())
                .nickname(userLoginDto.getNickname())
                .oauthDomain(userLoginDto.getSocialLoginInfo())
                .build();
    }

    public static User from(UserInfoDto userInfoDto) {
        return User.builder()
                .email(userInfoDto.getEmail())
                .name(userInfoDto.getName())
                .nickname(userInfoDto.getNickname())
                .oauthDomain(userInfoDto.getSocialLoginInfo())
                .build();
    }

}
