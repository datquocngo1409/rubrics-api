package com.example.demo.model.dto;

import com.example.demo.model.RubricImportant;
import com.example.demo.model.TranscriptInfomation;
import lombok.Data;

@Data
public class TranscriptInfomationDto {
    private Long id;
    private Long classroomId;
    private String classroomCode;
    private String classroomName;
    private Long teacherId;
    private String teacherName;
    private String rubricImportantList;

    public TranscriptInfomationDto() {
    }

    public TranscriptInfomationDto(TranscriptInfomation ti) {
        this.id = ti.getId();
        this.classroomId = ti.getClassroom().getId();
        this.classroomCode = ti.getClassroom().getCode();
        this.classroomName = ti.getClassroom().getName();
        this.teacherId = ti.getClassroom().getTeacher().getId();
        this.teacherName = ti.getClassroom().getTeacher().getName();
        String riList = "";
        if (ti.getRubricImportantList().size() > 0) {
            for (RubricImportant rubricImportant : ti.getRubricImportantList()) {
                riList = riList + rubricImportant.getId() + ", ";
            }
            riList = riList.substring(0, riList.length() - 2);
            this.rubricImportantList = riList;
        } else {
            this.rubricImportantList = "";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
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

    public String getRubricImportantList() {
        return rubricImportantList;
    }

    public void setRubricImportantList(String rubricImportantList) {
        this.rubricImportantList = rubricImportantList;
    }
}
