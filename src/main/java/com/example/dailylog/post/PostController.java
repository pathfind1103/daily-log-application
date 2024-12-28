package com.example.dailylog.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("boards/{boardId}")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable Long boardId) {
       return ResponseEntity.ok(postService.getAllPosts(boardId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long boardId,
                                            @PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostById(boardId,postId), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@PathVariable Long boardId,
                                             @RequestBody Post post) {
        boolean isPostCreated = postService.createPost(boardId, post);
        if (isPostCreated) {
            return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Post not created", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long boardId,
                                             @PathVariable Long postId) {
        boolean isPostDeleted = postService.deletePostById(boardId,postId);
        if (isPostDeleted) {
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not deleted", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long boardId,
                                             @PathVariable Long postId,
                                             @RequestBody Post updatedPost) {
        boolean updated = postService.updatePost(boardId, postId, updatedPost);
        if (updated) {
            return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not updated", HttpStatus.NOT_FOUND);
    }
}

/*

GET /posts: Get all posts
GET /posts/{id}: Get a post by ID
POST /posts: Create a new post
DELETE /posts/{id}: Delete a post by ID
PUT /posts/{id}: Update a post by ID

 */
