package com.example.backend.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
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

    public Post(){

    }

    public void edit(String title, String content) {
        this.title=title;
        this.content=content;

    }
}
