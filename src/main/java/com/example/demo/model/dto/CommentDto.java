package com.example.demo.model.dto;

import com.example.demo.model.Comment;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String userCommentName;
    private String userCommentCode;
    private String userCommentUsername;
    private String content;
    private Date creationTime;
    private Long rubricImportantId;
    private String rubricImportantName;
    private Long classroomId;
    private Long srpId;

    public CommentDto() {
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.userCommentCode = comment.getUserComment().getCode();
        this.userCommentName = comment.getUserComment().getName();
        this.userCommentUsername = comment.getUserComment().getUsername();
        this.content = comment.getContent();
        this.creationTime = comment.getCreationTime();
        this.srpId = comment.getSrp().getId();
        this.rubricImportantId = comment.getSrp().getRubricImportant().getId();
        this.rubricImportantName = comment.getSrp().getRubricImportant().getRubric().getName();
        this.classroomId = comment.getSrp().getRubricImportant().getClassroomId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCommentName() {
        return userCommentName;
    }

    public void setUserCommentName(String userCommentName) {
        this.userCommentName = userCommentName;
    }

    public String getUserCommentCode() {
        return userCommentCode;
    }

    public void setUserCommentCode(String userCommentCode) {
        this.userCommentCode = userCommentCode;
    }

    public String getUserCommentUsername() {
        return userCommentUsername;
    }

    public void setUserCommentUsername(String userCommentUsername) {
        this.userCommentUsername = userCommentUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getSrpId() {
        return srpId;
    }

    public void setSrpId(Long srpId) {
        this.srpId = srpId;
    }

    public Long getRubricImportantId() {
        return rubricImportantId;
    }

    public void setRubricImportantId(Long rubricImportantId) {
        this.rubricImportantId = rubricImportantId;
    }

    public String getRubricImportantName() {
        return rubricImportantName;
    }

    public void setRubricImportantName(String rubricImportantName) {
        this.rubricImportantName = rubricImportantName;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }


}
