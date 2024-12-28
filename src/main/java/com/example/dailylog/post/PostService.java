package com.example.dailylog.post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts(Long boardId);
    boolean  createPost(Long boardId, Post post);

    Post getPostById(Long boardId, Long postId);

    boolean deletePostById(Long boardId, Long postId);

    boolean updatePost(Long boardId, Long postId, Post updatedPost);
}
