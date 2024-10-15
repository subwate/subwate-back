package com.project.subwate_backend.application.subwayline.service;

import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.subwayline.entity.SubwayLine;
import com.project.subwate_backend.infrastructure.subwayline.repository.SubwayLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubwayLineServiceTest {

    @Mock
    private SubwayLineRepository subwayLineRepository;

    @InjectMocks
    private SubwayLineServiceImplement subwayLineServicet;

    @Mock
    private ResponseMapper responseMapper;

    private SubwayLine subwayLine;
    private List<SubwayLine> subwayLineList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        subwayLine = new SubwayLine(1001L, "1호선");
        subwayLineList = Arrays.asList(subwayLine, new SubwayLine(1002L, "2호선"));
    }

    @Test
    void testGetSubwayLine() {
        SubwayLineDto subwayLineDto = new SubwayLineDto();
        subwayLineDto.setId(1001L);
        subwayLineDto.setName("1호선");

        when(subwayLineRepository.findById(1001L)).thenReturn(Optional.of(subwayLine));
        when(responseMapper.toSubwayLineDto(subwayLine)).thenReturn(subwayLineDto);

        ResponseDto<SubwayLineDto> response = subwayLineServicet.getSubwayLine(1001L);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("1호선", response.getData().getName());
    }

    @Test
    void testGetSubwayLineList() {
        SubwayLineDto subwayLineDto1 = new SubwayLineDto();
        subwayLineDto1.setId(1001L);
        subwayLineDto1.setName("1호선");

        SubwayLineDto subwayLineDto2 = new SubwayLineDto();
        subwayLineDto2.setId(1002L);
        subwayLineDto2.setName("2호선");

        when(subwayLineRepository.findAllById(any())).thenReturn(subwayLineList);
        when(responseMapper.toSubwayLineDto(subwayLineList.get(0))).thenReturn(subwayLineDto1);
        when(responseMapper.toSubwayLineDto(subwayLineList.get(1))).thenReturn(subwayLineDto2);

        ResponseDto<List<SubwayLineDto>> response = subwayLineServicet
                .getSubwayLineList(Arrays.asList(1001L, 1002L));

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals(2, response.getData().size());
        assertEquals("1호선", response.getData().get(0).getName());
        assertEquals("2호선", response.getData().get(1).getName());
    }

}
