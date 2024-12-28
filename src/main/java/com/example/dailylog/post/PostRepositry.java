package com.example.dailylog.post;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepositry extends CrudRepository<Post, Long> {
    List<Post> findByBoardId (Long boardId);
}
