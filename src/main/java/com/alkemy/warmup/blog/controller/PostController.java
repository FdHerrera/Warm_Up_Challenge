package com.alkemy.warmup.blog.controller;

import com.alkemy.warmup.blog.model.Post;
import com.alkemy.warmup.blog.model.PostRequest;
import com.alkemy.warmup.blog.service.PostRequestService;
import com.alkemy.warmup.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService service;
    private final PostRequestService requestService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = service.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<Post>> getPostsByTitle(@RequestParam(value = "title", required = false) String title){
        List<Post> posts = service.getPostsByTitle(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(params = "category")
    public ResponseEntity<List<Post>> getPostsByCategory(@RequestParam(value = "category", required = false) String category){
        List<Post> posts = service.getPostsByCategory(category);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id){
        Post post = service.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Post> addPost(@RequestBody PostRequest postRequest){
        Post newPost = requestService.createNewPost(postRequest);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) throws Exception {
        Post updatedPost = service.updatePost(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePostByid(@PathVariable("id") Long id){
        service.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
