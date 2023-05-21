package com.example.backend.post;

import lombok.Data;

@Data
public class PostDTO {

    private final String title;

    private final String content;

    public PostDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
