package com.example.backend.post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {



    List<Post> findTop100ByTitleContainingOrderByCreatedAtDesc(String keyword);

    List<Post> findTop100ByOrderByCreatedAtDesc();
}
