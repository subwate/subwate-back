package com.project.subwate_backend.application.subwayline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.subwayline.entity.SubwayLine;
import com.project.subwate_backend.infrastructure.subwayline.repository.SubwayLineRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubwayLineServiceImplement implements SubwayLineService {

    SubwayLineRepository subwayLineRepository;
    ResponseMapper responseMapper;

    @Override
    public ResponseDto<SubwayLineDto> getSubwayLine(Long id) {

        SubwayLine subwayLine = subwayLineRepository.findById(id).orElse(null);

        if (subwayLine == null) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "해당 id의 호선을 찾을 수 없습니다.", null);
        }

        SubwayLineDto subwayLineDto = responseMapper.toSubwayLineDto(subwayLine);
        return ResponseDto.of(HttpStatus.OK, "호선 조회 성공", subwayLineDto);
    }

    @Override
    public ResponseDto<List<SubwayLineDto>> getSubwayLineList(List<Long> subwayLineIdList) {

        List<SubwayLine> subwayLineList = subwayLineRepository.findAllById(subwayLineIdList);

        if (subwayLineList.isEmpty()) {
            return ResponseDto.of(HttpStatus.NOT_FOUND, "해당 id의 호선을 찾을 수 없습니다.", null);
        }

        List<SubwayLineDto> subwayLineDtoList = subwayLineList.stream()
                .map(responseMapper::toSubwayLineDto)
                .collect(Collectors.toList());

        return ResponseDto.of(HttpStatus.OK, "호선 리스트 조회 성공", subwayLineDtoList);
    }
}
