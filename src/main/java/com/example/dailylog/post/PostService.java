package com.example.dailylog.post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    void createPost(Post post);

    Post getPostByID(Long id);

    boolean deletePostById(Long id);

    boolean updatePost(Long id, Post updatedPost);
}
