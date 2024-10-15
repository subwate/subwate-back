package com.project.subwate_backend.presentation.station.controller;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.application.station.service.StationService;
import com.project.subwate_backend.common.dto.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(StationController.class)
class StationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    private StationDto stationDto;
    private List<StationDto> stationDtoList;

    @BeforeEach
    void setUp() {
        stationDto = new StationDto();
        stationDto.setId(1L);
        stationDto.setName("서울역");
        stationDto.setLatitude(37.566535);
        stationDto.setLongitude(126.977969);

        stationDtoList = Arrays.asList(stationDto);
    }

    @Test
    void testGetNearestStation() throws Exception {
        when(stationService.getNearestStation(any(Double.class), any(Double.class)))
                .thenReturn(ResponseDto.of(HttpStatus.OK, "가장 가까운 역 조회 성공", stationDtoList));

        ResultActions result = mockMvc.perform(get("/api/v1/station/nearest")
                .param("latitude", "37.566535")
                .param("longitude", "126.977969"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.data[0].name").value("서울역"));
    }

    @Test
    void testGetStation() throws Exception {
        when(stationService.getStation(1L))
                .thenReturn(ResponseDto.of(HttpStatus.OK, "역 조회 성공", stationDto));

        ResultActions result = mockMvc.perform(get("/api/v1/station/1"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.data.name").value("서울역"));
    }
}
