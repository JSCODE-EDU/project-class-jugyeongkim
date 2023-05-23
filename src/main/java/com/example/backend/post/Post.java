package com.example.backend.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value="id")
    private Long id;
    @Column
    @JsonProperty(value="title")
    private String title;

    @Column
    @JsonProperty(value="content")
    private String content;

    @CreatedDate
    @Column(updatable = false,nullable=false)
    @JsonProperty(value="createdAt")
    private LocalDateTime createdAt;

    public Post(){

    }

    public void edit(String title, String content) {
        this.title=title;
        this.content=content;

    }
}
