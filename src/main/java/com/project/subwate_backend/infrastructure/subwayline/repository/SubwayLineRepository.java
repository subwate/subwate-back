package com.project.subwate_backend.infrastructure.subwayline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.subwate_backend.domain.subwayline.entity.SubwayLine;

public interface SubwayLineRepository extends JpaRepository<SubwayLine, Long> {

}
