package com.project.subwate_backend.presentation.community.dto.response;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "게시물 내용 클래스")
public class PostDto {
    Long contentId;
    Date createdAt;
    String title;
    @Schema(description = "호선Id", example = "1001")
    Long subwayLineId;
    String content;
    @Schema(description = "좋아요", example = "좋아요 개수")
    Long likes;
    @Parameter(description = "이미지 저장 경로 list")
    List<String> imageUrl;
    List<CommentDto> comments;

}
