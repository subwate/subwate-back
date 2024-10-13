package com.project.subwate_backend.infrastructure.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.subwate_backend.domain.station.entity.Station;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "SELECT id, subway_line_id, name, latitude, longitude, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) " +
            "* cos(radians(longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(latitude)))) AS distance " +
            "FROM station " +
            "ORDER BY distance ASC " +
            "LIMIT 1", nativeQuery = true)
    Station findNearestStation(@Param("latitude") double latitude, @Param("longitude") double longitude);

    List<Station> findByName(String name);
}
