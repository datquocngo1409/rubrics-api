package com.example.demo.model.dto;

import com.example.demo.model.RequestRubric;
import lombok.Data;

@Data
public class RequestRubricDto {
    private Long id;
    private String name;
    private boolean created;

    public RequestRubricDto() {
    }

    public RequestRubricDto(RequestRubric requestRubric) {
        this.id = requestRubric.getId();
        this.name = requestRubric.getName();
        this.created = requestRubric.isCreated();
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
