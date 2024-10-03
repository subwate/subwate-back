package com.project.subwate_backend.presentation.community.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(description = "게시물 내용 클래스")
public class PostRequestDto {
    String title;
    @Schema(description = "호선Id", example = "1001")
    Long subwayLineId;
    String content;
    @Parameter(description = "업로드한 이미지")
    List<MultipartFile> imageUrl;

}
