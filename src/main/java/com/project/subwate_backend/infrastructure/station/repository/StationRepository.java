package com.project.subwate_backend.infrastructure.station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.project.subwate_backend.domain.station.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long>,
                QuerydslPredicateExecutor<Station>, StationRepositoryCustom {

}