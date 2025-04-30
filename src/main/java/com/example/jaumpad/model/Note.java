package com.example.jaumpad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Note {

    @Id
    private String id;
    private String content;
    private LocalDateTime lastModified;

    public Note() {}

    public Note(String id, String content) {
        this.id = id;
        this.content = content;
        this.lastModified = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
}