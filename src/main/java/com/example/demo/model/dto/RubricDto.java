package com.example.demo.model.dto;

import com.example.demo.model.Rubric;
import lombok.Data;

@Data
public class RubricDto {
    private Long id;
    private String name;

    public RubricDto() {
    }

    public RubricDto(Rubric rubric) {
        this.id = rubric.getId();
        this.name = rubric.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
