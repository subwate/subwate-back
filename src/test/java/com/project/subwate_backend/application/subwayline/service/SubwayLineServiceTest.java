package com.project.subwate_backend.application.subwayline.service;

import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.common.dto.ResponseDto;
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
import static org.mockito.Mockito.*;

class SubwayLineServiceTest {

    @Mock
    private SubwayLineRepository subwayLineRepository;

    @InjectMocks
    private SubwayLineServiceImplement subwayLineServiceImplement;

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
        when(subwayLineRepository.findById(1001L)).thenReturn(Optional.of(subwayLine));

        ResponseDto<SubwayLineDto> response = subwayLineServiceImplement.getSubwayLine(1001L);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("1호선", response.getData().getSubwayLineName());
    }

    @Test
    void testGetSubwayLineList() {
        when(subwayLineRepository.findAllById(any())).thenReturn(subwayLineList);

        ResponseDto<List<SubwayLineDto>> response = subwayLineServiceImplement
                .getSubwayLineList(Arrays.asList(1001L, 1002L));

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals(2, response.getData().size());
        assertEquals("1호선", response.getData().get(0).getSubwayLineName());
        assertEquals("2호선", response.getData().get(1).getSubwayLineName());
    }
}
