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
        Pageable pageable= PageRequest.of(0,100);
        Page<Post> pages=postRepository.findAllByOrderByCreatedAtDesc(pageable);

        if (!pages.hasNext()){
            pages=new PageImpl<>(pages.getContent());
        }
        return pages.getContent();
    }


    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    @Transactional
    public Optional<Post> edit(Long id, PostDTO postDTO) {
        Post newpost=postRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
        newpost.edit(postDTO.getTitle(),postDTO.getContent());
        return Optional.of(newpost);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
        
    }


    public List<Post> search(String keyword) {
        Pageable pageable= PageRequest.of(0,100);
        Page<Post> pages=postRepository.findByTitleContainingOrderByCreatedAtDesc(keyword,pageable);

        if (!pages.hasNext()){
            pages=new PageImpl<>(pages.getContent());
        }
        return pages.getContent();
    }
}
