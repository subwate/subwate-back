package com.project.subwate_backend.presentation.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Schema(description = "게시물 댓글의 대댓글 클래스")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReplyDto {
    Long replyId;
    Date createAt;
    String content;
}
