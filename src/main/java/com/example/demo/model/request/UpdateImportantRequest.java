package com.example.demo.model.request;

import lombok.Data;

@Data
public class UpdateImportantRequest {
    private Long rubricImportantId;
    private double newImportant;

    public UpdateImportantRequest() {
    }

    public UpdateImportantRequest(Long rubricImportantId, double newImportant) {
        this.rubricImportantId = rubricImportantId;
        this.newImportant = newImportant;
    }

    public Long getRubricImportantId() {
        return rubricImportantId;
    }

    public void setRubricImportantId(Long rubricImportantId) {
        this.rubricImportantId = rubricImportantId;
    }

    public double getNewImportant() {
        return newImportant;
    }

    public void setNewImportant(double newImportant) {
        this.newImportant = newImportant;
    }
}
