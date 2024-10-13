package com.project.subwate_backend.application.subwayline.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.application.subwayline.service.SubwayLineService;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.domain.subwayline.entity.SubwayLine;
import com.project.subwate_backend.infrastructure.subwayline.repository.SubwayLineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubwayLineServiceImplement implements SubwayLineService {

    private final SubwayLineRepository subwayLineRepository;

    @Override
    public ResponseDto<SubwayLineDto> getSubwayLine(Long id) {

        Optional<SubwayLine> subwayLineOpt = subwayLineRepository.findById(id);

        if (subwayLineOpt.isPresent()) {
            SubwayLine subwayLine = subwayLineOpt.get();
            SubwayLineDto subwayLineDto = new SubwayLineDto(subwayLine.getId(), subwayLine.getName());
            return ResponseDto.of(HttpStatus.OK, "호선 조회 성공", subwayLineDto);
        } else {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "해당 id의 호선을 찾을 수 없습니다.", null);
        }
    }

    @Override
    public ResponseDto<List<SubwayLineDto>> getSubwayLineList(List<Long> subwayLineIdList) {

        List<SubwayLine> subwayLines = subwayLineRepository.findAllById(subwayLineIdList);

        List<SubwayLineDto> subwayLineDtoList = subwayLines.stream()
                .map(subwayLine -> new SubwayLineDto(subwayLine.getId(), subwayLine.getName()))
                .collect(Collectors.toList());

        if (subwayLineDtoList.isEmpty()) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "해당 id의 호선을 찾을 수 없습니다.", null);
        } else {
            return ResponseDto.of(HttpStatus.OK, "호선 리스트 조회 성공", subwayLineDtoList);
        }
    }
}
