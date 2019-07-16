package com.example.reactive.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Tweet {

    private String id;

    private String text;

    private Date createdAt = new Date();

    public Tweet() {

    }

    public Tweet(String id, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Tweet(String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
