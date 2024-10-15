package com.project.subwate_backend.presentation.subwayline.controller;

import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.application.subwayline.service.SubwayLineService;
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
@WebMvcTest(SubwayLineController.class)
class SubwayLineControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private SubwayLineService subwayLineService;

        private SubwayLineDto subwayLineDto;
        private List<SubwayLineDto> subwayLineDtoList;

        @BeforeEach
        void setUp() {
                subwayLineDto = new SubwayLineDto();
                subwayLineDto.setId(1001L);
                subwayLineDto.setName("1호선");
                subwayLineDtoList = Arrays.asList(subwayLineDto);
        }

        @Test
        void testGetSubwayLine() throws Exception {
                when(subwayLineService.getSubwayLine(1001L))
                                .thenReturn(ResponseDto.of(HttpStatus.OK, "호선 조회 성공", subwayLineDto));

                ResultActions result = mockMvc.perform(get("/api/v1/line/1001"));

                result.andExpect(status().isOk())
                                .andExpect(jsonPath("$.httpStatus").value("OK"))
                                .andExpect(jsonPath("$.data.name").value("1호선"));
        }

        @Test
        void testGetSubwayLineList() throws Exception {
                when(subwayLineService.getSubwayLineList(any()))
                                .thenReturn(ResponseDto.of(HttpStatus.OK, "호선 리스트 조회 성공", subwayLineDtoList));

                ResultActions result = mockMvc.perform(get("/api/v1/line/list")
                                .param("subwayLineIdList", "1001,1002"));

                result.andExpect(status().isOk())
                                .andExpect(jsonPath("$.httpStatus").value("OK"))
                                .andExpect(jsonPath("$.data[0].name").value("1호선"));
        }
}
