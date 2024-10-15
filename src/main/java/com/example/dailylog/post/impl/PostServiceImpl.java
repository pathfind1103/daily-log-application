package com.example.dailylog.post.impl;

import com.example.dailylog.post.Post;
import com.example.dailylog.post.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private List<Post> posts = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public void createPost(Post post) {
        post.setId(nextId++);
        posts.add(post);
    }

    @Override
    public Post getPostByID(Long id) {
        for (Post post: posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }

    @Override
    public boolean deletePostById(Long id) {
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePost(Long id, Post updatedPost) {
        for (Post post: posts) {
            if (post.getId().equals(id)) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setCreatedAt(updatedPost.getCreatedAt());
                post.setUpdatedAt(updatedPost.getUpdatedAt());
                post.setBoard(updatedPost.getBoard());
                post.setTags(updatedPost.getTags());
                return true;
            }
        }
        return false;
    }
}
