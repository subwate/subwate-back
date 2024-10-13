package com.project.subwate_backend.application.subwayline.service;

import java.util.List;
import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.common.dto.ResponseDto;

public interface SubwayLineService {

    ResponseDto<SubwayLineDto> getSubwayLine(Long id);
    
    ResponseDto<List<SubwayLineDto>> getSubwayLineList(List<Long> subwayLineIdList); 
}
