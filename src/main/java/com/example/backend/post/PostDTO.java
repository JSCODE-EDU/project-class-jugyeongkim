package com.example.backend.post;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class PostDTO {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(min=1,max=15, message = "제목의 길이는 1이상 15이하 입니다.")
    private final String title;

    @NotBlank(message="내용은 필수 입력 값입니다.")
    @Size(min=1,max=1000,message = "내용의 길이는 1이상 1000이하 입니다.")
    private final String content;

    private final LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @Builder
    public PostDTO(String title, String content, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post toEntity(){
        return Post.builder().title(title).content(content).build();
    }


    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
