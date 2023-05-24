package com.example.backend.post;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        postRepository.save(post);
        return post;
    }

    public List<Post> findAll() {

        return postRepository.findTop100ByOrderByCreatedAtDesc();
    }


    public Optional<Post> findById(Long id) {
        Post newpost=postRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
        return Optional.of(newpost);
    }
    @Transactional
    public Optional<Post> edit(Long id, PostDTO postDTO) {
        Post newpost=postRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
        newpost.edit(postDTO.getTitle(),postDTO.getContent());
        return Optional.of(newpost);
    }

    public void deleteById(Long id) {
        postRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
        postRepository.deleteById(id);
        
    }


    public List<Post> search(String keyword) {
        return postRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);
    }
}
