package com.project.subwate_backend.domain.subwayline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class SubwayLine {
    
    @Id
    private Long id;
    private String name;
}
