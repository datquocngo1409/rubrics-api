package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UpdatePointDto {
    private Long studentId;
    private Long rubricImportantId;
    private double point;
}
