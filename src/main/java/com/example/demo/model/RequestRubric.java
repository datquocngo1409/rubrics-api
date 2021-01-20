package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_RequestRubric")
public class RequestRubric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean created = false;

    public RequestRubric() {
    }

    public RequestRubric(Long id, String name) {
        this.id = id;
        this.name = name;
        this.created = false;
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

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}
