package com.example.backend.post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RequestMapping("api/posts")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@Valid @RequestBody PostDTO postdto, BindingResult bindingResult){
        ResponseEntity<Object> errorResponse = handleBindingErrors(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        Post post=postService.save(postdto);
        return ResponseEntity.ok(post);
    }
    @GetMapping("")
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping(value="find",params="id")
    public ResponseEntity<Object> findById(@RequestParam Long id){
        Optional<Post> post=postService.findById(id);
        if (post.isEmpty()){
            String errorMessage="해당 id의 게시글이 존재하지 않습니다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.ok(post.get());
    }

    @PutMapping(value="",params="id")
    public ResponseEntity<Object> edit(@RequestParam Long id, @Valid @RequestBody PostDTO postDTO,BindingResult bindingResult)
    {
        ResponseEntity<Object> errorResponse = handleBindingErrors(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        Optional<Post> post = postService.edit(id, postDTO);
        return ResponseEntity.ok(post);
    }



    @DeleteMapping(value="",params="id")
    public ResponseEntity<Object> delete(@RequestParam Long id){
        Optional<Post> post=postService.findById(id);
        if (post.isEmpty()){
            String errorMessage="해당 id의 게시글이 존재하지 않습니다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        postService.deleteById(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    @GetMapping(value="search",params="keyword")
    public ResponseEntity<Object> search(@RequestParam String keyword){
        String newkeyword = keyword.replaceAll(" ", "");
        if (newkeyword.length()<1) {
            String errorMessage="검색 키워드는 공백을 제외한 1글자 이상이어야 한다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        List<Post> posts=postService.search(keyword);
        return ResponseEntity.ok(posts);
    }

    private ResponseEntity<Object> handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }
}
