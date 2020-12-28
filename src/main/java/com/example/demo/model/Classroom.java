package com.example.demo.model;

import com.example.demo.model.dto.ClassroomDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_Classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User teacher;

    @ManyToMany
    private List<User> students;

    @Column(unique = true)
    private String code;

    private String name;

    private String startDate;

    public Classroom() {
    }

    public Classroom(User teacher, List<User> students, String code, String name, String startDate) {
        this.teacher = teacher;
        this.students = students;
        this.code = code;
        this.name = name;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
