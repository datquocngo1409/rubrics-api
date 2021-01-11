package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromUser;

    private String toUser;

    private String type;

    private String content;

    private boolean seen;

    public Notification() {
    }

    public Notification(Long id, String from, String to, String type, String content) {
        this.id = id;
        this.fromUser = from;
        this.toUser = to;
        this.type = type;
        this.content = content;
        this.seen = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return fromUser;
    }

    public void setFrom(String from) {
        this.fromUser = from;
    }

    public String getTo() {
        return toUser;
    }

    public void setTo(String to) {
        this.toUser = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
