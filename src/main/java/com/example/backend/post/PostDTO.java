package com.example.backend.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PostDTO(String title, String content, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
