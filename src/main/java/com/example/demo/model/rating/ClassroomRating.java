package com.example.demo.model.rating;

import com.example.demo.model.Classroom;

import javax.persistence.*;

@Entity
@Table(name = "tbl_ClassroomRating")
public class ClassroomRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Classroom subject;

    private double point = 0;

    private int count = 0;

    public ClassroomRating() {
    }

    public ClassroomRating(Classroom subject) {
        this.subject = subject;
        this.point = 0;
        this.count = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classroom getSubject() {
        return subject;
    }

    public void setSubject(Classroom subject) {
        this.subject = subject;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
