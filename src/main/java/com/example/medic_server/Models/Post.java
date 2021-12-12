package com.example.medic_server.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {
    @NonNull
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private String tags;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;

    @ElementCollection
    private List<String> images;

    public Post() {
        images = new ArrayList<>();
    }

    public Post(String title, String body, String tags) {
        this();
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.date = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }
}
