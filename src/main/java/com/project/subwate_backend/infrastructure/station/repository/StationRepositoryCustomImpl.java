package com.project.subwate_backend.infrastructure.station.repository;

import com.project.subwate_backend.domain.station.entity.QStation;
import com.project.subwate_backend.domain.station.entity.Station;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StationRepositoryCustomImpl implements StationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Station> getNearestStation(double latitude, double longitude) {
        QStation station = QStation.station;

        // latitude 를 radians 로 계산
        NumberExpression<Double> radiansLatitude = Expressions.numberTemplate(Double.class, "radians({0})", latitude);

        // 계산된 latitude -> 코사인 계산
        NumberExpression<Double> cosLatitude = Expressions.numberTemplate(Double.class, "cos({0})", radiansLatitude);
        NumberExpression<Double> cosstationLatitude = Expressions.numberTemplate(Double.class, "cos(radians({0}))",
                station.latitude);

        // 계산된 latitude -> 사인 계산
        NumberExpression<Double> sinLatitude = Expressions.numberTemplate(Double.class, "sin({0})", radiansLatitude);
        NumberExpression<Double> sinstationLatitude = Expressions.numberTemplate(Double.class, "sin(radians({0}))",
                station.latitude);

        // 사이 거리 계산
        NumberExpression<Double> cosLongitude = Expressions.numberTemplate(Double.class,
                "cos(radians({0}) - radians({1}))", station.longitude, longitude);

        NumberExpression<Double> acosExpression = Expressions.numberTemplate(Double.class, "acos({0})", cosLatitude
                .multiply(cosstationLatitude).multiply(cosLongitude).add(sinLatitude.multiply(sinstationLatitude)));

        // 최종 거리 계산
        NumberExpression<Double> distanceExpression = Expressions.numberTemplate(Double.class, "6371 * {0}",
                acosExpression);

        // 가장 가까운 역 이름
        String nearestStationName = queryFactory.select(station.name)
                .from(station)
                .orderBy(distanceExpression.asc())
                .limit(1)
                .fetchOne();

        // 해당 역 이름과 일치하는 모든 Station 반환
        return queryFactory.selectFrom(station)
                .where(station.name.eq(nearestStationName))
                .fetch();
        }
}
