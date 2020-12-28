package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_TranscriptInfomation")
public class TranscriptInfomation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Classroom classroom;

    @ManyToMany
    private List<RubricImportant> rubricImportantList;

    public TranscriptInfomation() {
    }

    public TranscriptInfomation(Classroom classroom, List<RubricImportant> rubricImportantList) {
        this.classroom = classroom;
        this.rubricImportantList = rubricImportantList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<RubricImportant> getRubricImportantList() {
        return rubricImportantList;
    }

    public void setRubricImportantList(List<RubricImportant> rubricImportantList) {
        this.rubricImportantList = rubricImportantList;
    }
}
