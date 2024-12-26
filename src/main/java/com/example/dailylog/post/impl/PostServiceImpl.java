package com.example.dailylog.post.impl;

import com.example.dailylog.post.Post;
import com.example.dailylog.post.PostRepositry;
import com.example.dailylog.post.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    PostRepositry postRepositry;

    public PostServiceImpl(PostRepositry postRepositry) {
        this.postRepositry = postRepositry;
    }

    @Override
    public List<Post> findAll() {
        return (List<Post>) postRepositry.findAll();
    }

    @Override
    public void createPost(Post post) {
        postRepositry.save(post);
    }

    @Override
    public Post getPostByID(Long id) {
        return postRepositry.findById(id).orElse(null);
    }

    @Override
    public boolean deletePostById(Long id) {
        if (postRepositry.existsById(id)) {
            postRepositry.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePost(Long id, Post updatedPost) {
        Optional<Post> postOptional = postRepositry.findById(id);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setCreatedAt(updatedPost.getCreatedAt());
                post.setUpdatedAt(updatedPost.getUpdatedAt());
//                post.setBoard(updatedPost.getBoard());
                postRepositry.save(post);
                return true;
            }
        return false;
    }
}
