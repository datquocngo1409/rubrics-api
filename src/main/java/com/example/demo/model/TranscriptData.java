package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_TranscriptData")
public class TranscriptData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private TranscriptInfomation transcriptInfomation;

    @OneToOne
    private Classroom classroom;

    @OneToMany
    private List<StudentTotalRubricPoint> studentTotalRubricPoints;

    public TranscriptData() {
    }

    public TranscriptData(TranscriptInfomation transcriptInfomation, Classroom classroom, List<StudentTotalRubricPoint> studentTotalRubricPoints) {
        this.transcriptInfomation = transcriptInfomation;
        this.classroom = classroom;
        this.studentTotalRubricPoints = studentTotalRubricPoints;
    }

    public TranscriptData(Long id, TranscriptInfomation transcriptInfomation, Classroom classroom, List<StudentTotalRubricPoint> studentTotalRubricPoints) {
        this.id = id;
        this.transcriptInfomation = transcriptInfomation;
        this.classroom = classroom;
        this.studentTotalRubricPoints = studentTotalRubricPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TranscriptInfomation getTranscriptInnfomation() {
        return transcriptInfomation;
    }

    public void setTranscriptInnfomation(TranscriptInfomation transcriptInfomation) {
        this.transcriptInfomation = transcriptInfomation;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<StudentTotalRubricPoint> getStudentTotalRubricPoints() {
        return studentTotalRubricPoints;
    }

    public void setStudentTotalRubricPoints(List<StudentTotalRubricPoint> studentTotalRubricPoints) {
        this.studentTotalRubricPoints = studentTotalRubricPoints;
    }
}
