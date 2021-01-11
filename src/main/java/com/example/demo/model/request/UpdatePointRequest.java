package com.example.demo.model.request;

import lombok.Data;

@Data
public class UpdatePointRequest {
    private Long studentId;
    private Long rubricImportantId;
    private double point;
}
