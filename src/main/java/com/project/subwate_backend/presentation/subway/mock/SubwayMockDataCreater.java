package com.project.subwate_backend.presentation.subway.mock;

import com.project.subwate_backend.presentation.subway.dto.NotificationDto;
import com.project.subwate_backend.presentation.subway.dto.RouteBookmarkDto;
import com.project.subwate_backend.presentation.subway.dto.RouteDto;
import com.project.subwate_backend.presentation.subway.dto.StationDto;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;
import com.project.subwate_backend.presentation.subway.dto.enums.ArrivalStatus;
import com.project.subwate_backend.presentation.subway.dto.enums.SubwayType;
import com.project.subwate_backend.presentation.subway.dto.enums.UpDownClassification;
import java.util.List;
import java.util.ArrayList;


public class SubwayMockDataCreater {

    public static SubwayDto createMockSubway() {

        StationDto stationDto = new StationDto();
        stationDto.setStationId((long) 1001000133);
        stationDto.setStationName("서울");

        SubwayDto subwayDto = new SubwayDto();
        subwayDto.setSubwayLineId((long) 1001);
        subwayDto.setSubwayLineName("1호선");
        subwayDto.setCurrentStationId((long) 1001000133);
        subwayDto.setPreviousStationId((long) 1001000133);
        subwayDto.setNextStationId((long) 1001000133);
        subwayDto.setTransitRoutesCount(0);
        subwayDto.setReceivedTime("2024-09-29 10:35:15");
        subwayDto.setArrivalStatus(ArrivalStatus.fromCode("3"));
        subwayDto.setSubwayType(SubwayType.fromDescription("급행"));
        subwayDto.setUpDownClassification(UpDownClassification.fromDescription("상행"));
        subwayDto.setExpectedArrivalTime(300);
        return subwayDto;
    }

    public static StationDto createMockStation() {
        StationDto stationDto = new StationDto();
        stationDto.setStationId((long) 1001000134);
        stationDto.setStationName("서울");
        stationDto.setLatitude(37.55315);
        stationDto.setLongitude(126.972533);
        return stationDto;
    }

    public static RouteDto createMockRoute() {
        StationDto stationDto = SubwayMockDataCreater.createMockStation();
        RouteDto routeDto = new RouteDto();
        routeDto.setRouteId((long) 123);
        routeDto.setDepartureStation(stationDto);
        routeDto.setArrivalStation(stationDto);
        return routeDto;
    }

    public static List<RouteDto> createMockRouteList() {
        List<RouteDto> routeDtoList = new ArrayList<>();
        routeDtoList.add(SubwayMockDataCreater.createMockRoute());
        routeDtoList.add(SubwayMockDataCreater.createMockRoute());
        routeDtoList.add(SubwayMockDataCreater.createMockRoute());
        return routeDtoList;
    }

    public static RouteBookmarkDto createMockRouteBookmark() {

        RouteDto routeDto = SubwayMockDataCreater.createMockRoute();
        RouteBookmarkDto routeFavoriteDto = new RouteBookmarkDto();
        routeFavoriteDto.setRouteId(routeDto.getRouteId());
        routeFavoriteDto.setRouteName("아 출근하기 싫다");
        routeFavoriteDto.setNotificationDays("월,화,수,목,금");
        routeFavoriteDto.setNotificationTime("18:00");
        routeFavoriteDto.setIconUrl("subwate-icon.png");
        return routeFavoriteDto;
    }

    public static List<RouteBookmarkDto> createMockRouteBookmarkList() {
        List<RouteBookmarkDto> routeBookmarkList = new ArrayList<>();
        routeBookmarkList.add(SubwayMockDataCreater.createMockRouteBookmark());
        routeBookmarkList.add(SubwayMockDataCreater.createMockRouteBookmark());
        routeBookmarkList.add(SubwayMockDataCreater.createMockRouteBookmark());
        return routeBookmarkList;
    }

    public static NotificationDto createMockNotification() {

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationId((long) 123);
        notificationDto.setStationId((long) 1001000134);
        return notificationDto;
    }

}
