package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User userComment;

    @ManyToOne
    private StudentRubricPoint srp;

    private Date creationTime = new Date();

    private String content;

    public Comment() {
    }

    public Comment(User userComment, StudentRubricPoint srp, String content) {
        this.userComment = userComment;
        this.srp = srp;
        this.creationTime = new Date();
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }

    public StudentRubricPoint getSrp() {
        return srp;
    }

    public void setSrp(StudentRubricPoint srp) {
        this.srp = srp;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
