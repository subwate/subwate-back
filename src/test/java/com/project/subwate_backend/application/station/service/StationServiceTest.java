package com.project.subwate_backend.application.station.service;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.station.entity.Station;
import com.project.subwate_backend.infrastructure.station.repository.StationRepository;
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
import static org.mockito.Mockito.when;

class StationServiceTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationServiceImpl stationService;

    @Mock
    private ResponseMapper responseMapper;

    private Station station;
    private List<Station> stationList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        station = new Station(1L, 1001L, "서울역", 37.566535, 126.977969);
        stationList = Arrays.asList(station);

        StationDto stationDto = new StationDto();
        stationDto.setId(1L);
        stationDto.setName("서울역");
        when(responseMapper.toStationDto(station)).thenReturn(stationDto);
    }

    @Test
    void testGetStation() {
        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));

        ResponseDto<StationDto> response = stationService.getStation(1L);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals("서울역", response.getData().getName());
    }

    @Test
    void testGetNearestStation() {
        when(stationRepository.getNearestStation(any(Double.class), any(Double.class)))
                .thenReturn(stationList);

        ResponseDto<List<StationDto>> response = stationService.getNearestStation(37.566535, 126.977969);

        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals(1, response.getData().size());
        assertEquals("서울역", response.getData().get(0).getName());
    }

    @Test
    void testGetStation_NotFound() {
        when(stationRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseDto<StationDto> response = stationService.getStation(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertEquals(null, response.getData());
    }

    @Test
    void testGetNearestStation_NotFound() {
        when(stationRepository.getNearestStation(any(Double.class), any(Double.class)))
                .thenReturn(Arrays.asList());

        ResponseDto<List<StationDto>> response = stationService.getNearestStation(37.566535, 126.977969);

        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertEquals(null, response.getData());
    }
}
