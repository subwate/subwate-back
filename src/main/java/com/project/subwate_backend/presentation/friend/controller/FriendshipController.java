package com.project.subwate_backend.presentation.friend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.friend.dto.FriendCodeDto;
import com.project.subwate_backend.presentation.friend.dto.FriendDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "friendship")
@RestController
@RequestMapping("api/v1/friend")
public class FriendshipController {

    @Operation(summary = "친구 코드 생성")
    @PostMapping("/code")
    private static ResponseDto<FriendCodeDto> createFriendCode() {

        String code = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);
        FriendCodeDto friendCode = new FriendCodeDto(code, expirationTime);

        return ResponseDto.of(HttpStatus.OK, "친구 코드 생성에 성공했습니다.", friendCode);
    }

    @Operation(summary = "친구 추가")
    @PostMapping("")
    public ResponseDto<Void> addFriend(
            @Parameter(description = "친구 코드") @RequestBody String friendCode) {

        return ResponseDto.of(HttpStatus.OK, "친구 추가에 성공했습니다.", null);
    }

    @Operation(summary = "친구 조회")
    @GetMapping("/{friendId}")
    public ResponseDto<FriendDto> readFreind(@PathVariable Long friendId) {

        FriendDto friend = new FriendDto((long) 79, "친구 닉네임", "친구 프로필 이미지 URL");

        return ResponseDto.of(HttpStatus.OK, "친구 조회에 성공했습니다.", friend);
    }

    @Operation(summary = "모든 친구 조회")
    @GetMapping("")
    public ResponseDto<List<FriendDto>> readAllFreind() {

        FriendDto friend = new FriendDto((long) 79, "친구 닉네임", "친구 프로필 이미지 URL");
        List<FriendDto> friendList = new ArrayList<>();
        friendList.add(friend);
        friendList.add(friend);
        friendList.add(friend);

        return ResponseDto.of(HttpStatus.OK, "친구 조회에 성공했습니다.", friendList);
    }

    @Operation(summary = "친구 삭제")
    @DeleteMapping("/{friendId}")
    public ResponseDto<Void> deleteFreind(@PathVariable Long friendId) {

        return ResponseDto.of(HttpStatus.OK, "친구 삭제에 성공했습니다.", null);
    }
}