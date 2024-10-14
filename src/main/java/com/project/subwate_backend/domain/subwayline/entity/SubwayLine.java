package com.project.subwate_backend.domain.subwayline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class SubwayLine {
    
    @Id
    private Long id;
    private String name;
}
