package com.project.subwate_backend.presentation.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "게시물 댓글 클래스")
public class CommentDto {
    Long commentId;
    Date createAt;
    String content;
    List<ReplyDto> replies;
}
