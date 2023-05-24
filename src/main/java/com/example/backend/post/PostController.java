package com.example.backend.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequestMapping("api/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public Post save(@RequestBody PostDTO postdto){
        return postService.save(postdto);

    }
    @GetMapping("")
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping(value="",params="id")
    public Optional<Post> findById(@RequestParam Long id){
        return postService.findById(id);
    }

    @PutMapping(value="edit",params="id")
    public Optional<Post> edit(@RequestParam Long id, @RequestBody PostDTO postDTO)
    {
        return postService.edit(id, postDTO);
    }

    @DeleteMapping(value="delete",params="id")
    public String delete(@RequestParam Long id){
        postService.deleteById(id);
        return "삭제하였습니다";
    }

    @GetMapping(value="search",params="keyword")
    public List<Post> search(@RequestParam String keyword){
        return postService.search(keyword);
    }


}
