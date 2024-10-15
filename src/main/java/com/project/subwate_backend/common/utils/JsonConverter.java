package com.project.subwate_backend.common.utils;

import com.project.subwate_backend.presentation.subway.dto.SubwayDto;
import com.project.subwate_backend.presentation.subway.dto.enums.ArrivalStatus;
import com.project.subwate_backend.presentation.subway.dto.enums.SubwayType;
import com.project.subwate_backend.presentation.subway.dto.enums.UpDownClassification;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

    public SubwayDto convertToSubwayDto(Map<String, Object> jsonObject) throws ParseException {

        SubwayDto subwayDto = new SubwayDto();

        subwayDto.setSubwayId((String) jsonObject.get("btrainNo"));
        subwayDto.setSubwayLineId(parseLong((String) jsonObject.get("subwayId")));
        subwayDto.setSubwayLineName((String) jsonObject.get("trainLineNm"));
        subwayDto.setUpDownClassification(UpDownClassification.fromDescription((String) jsonObject.get("updnLine")));
        subwayDto.setCurrentStationId(parseLong((String) jsonObject.get("statnId")));
        subwayDto.setPreviousStationId(parseLong((String) jsonObject.get("statnFid")));
        subwayDto.setNextStationId(parseLong((String) jsonObject.get("statnTid")));
        subwayDto.setTransitRoutesCount(parseInt((String) jsonObject.get("trnsitCo")));
        subwayDto.setReceivedTime((String) jsonObject.get("recptnDt"));
        subwayDto.setArrivalStatus(ArrivalStatus.fromCode((String) jsonObject.get("arvlCd")));
        subwayDto.setSubwayType(SubwayType.fromDescription((String) jsonObject.get("btrainSttus")));
        subwayDto.setExpectedArrivalTime(parseInt((String) jsonObject.get("barvlDt")));

        return subwayDto;
    }

    private long parseLong(String value) {
        try {
            return value != null && !value.isEmpty() ? Long.parseLong(value) : 0L;
        } catch (Exception e) {
            return 0L;
        }
    }

    private int parseInt(String value) {
        try {
            return value != null && !value.isEmpty() ? Integer.parseInt(value) : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}
