package com.project.subwate_backend.application.subwayline.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SubwayLineDto {

    @Schema(description = "호선Id", example = "1001")
    public Long id;

    @Schema(description = "호선 이름", example = "2호선")
    public String name;
}
