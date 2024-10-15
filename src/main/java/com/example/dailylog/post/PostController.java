package com.example.dailylog.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
       return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostByID(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postService.createPost(post);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        boolean deleted = postService.deletePostById(id);
        if (deleted) {
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id,
                                             @RequestBody Post updatedPost) {
        boolean updated = postService.updatePost(id, updatedPost);
        if (updated) {
            return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

/*

GET /posts: Get all posts
GET /posts/{id}: Get a post by ID
POST /posts: Create a new post
DELETE /posts/{id}: Delete a post by ID
PUT /posts/{id}: Update a post by ID

 */
