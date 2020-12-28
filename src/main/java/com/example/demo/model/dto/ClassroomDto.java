package com.example.demo.model.dto;

import com.example.demo.model.Classroom;
import com.example.demo.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassroomDto {
    private Long id;
    private Long teacherId;
    private String teacherName;
    private List<UserDto> students;
    private String code;
    private String name;
    private String startDate;

    public ClassroomDto() {
    }

    public ClassroomDto(Classroom classroom) {
        this.id = classroom.getId();
        this.teacherId = classroom.getTeacher().getId();
        this.teacherName = classroom.getTeacher().getName();
        this.code = classroom.getCode();
        this.name = classroom.getName();
        this.startDate = classroom.getStartDate();
        if (classroom.getStudents().size() > 0) {
            List<UserDto> studentDtos = new ArrayList<>();
            for (User student : classroom.getStudents()) {
                UserDto studentDto = new UserDto(student);
                studentDtos.add(studentDto);
            }
            this.students = studentDtos;
        } else {
            this.students = new ArrayList<>();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<UserDto> getStudents() {
        return students;
    }

    public void setStudents(List<UserDto> students) {
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
