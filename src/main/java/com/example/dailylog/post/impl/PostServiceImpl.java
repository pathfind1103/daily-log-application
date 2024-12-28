package com.example.dailylog.post.impl;

import com.example.dailylog.board.Board;
import com.example.dailylog.board.BoardRepositry;
import com.example.dailylog.board.BoardService;
import com.example.dailylog.post.Post;
import com.example.dailylog.post.PostRepositry;
import com.example.dailylog.post.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepositry postRepositry;
    private final BoardService boardService;

    public PostServiceImpl(PostRepositry postRepositry, BoardService boardService) {
        this.postRepositry = postRepositry;
        this.boardService = boardService;
    }

    @Override
    public List<Post> getAllPosts(Long boardId) {
        return postRepositry.findByBoardId(boardId);
    }

    @Override
    public boolean createPost(Long boardId, Post post) {
        Board board = boardService.getBoardById(boardId);
        if (board != null) {
            post.setBoard(board);
            postRepositry.save(post);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Post getPostById(Long boardId, Long postId) {
        List<Post> posts = postRepositry.findByBoardId(boardId);
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deletePostById(Long boardId, Long postId) {
        if (boardService.getBoardById(boardId) != null
                && postRepositry.existsById(boardId)) {
            Post post = postRepositry.findById(postId).orElse(null);
            Board board = post.getBoard();
            board.getPosts().remove(post);
            post.setBoard(null);
            boardService.updateBoard(boardId, board);
            postRepositry.deleteById(postId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePost(Long boardId, Long postId, Post updatedPost) {
        if (boardService.getBoardById(boardId) != null) {
            updatedPost.setBoard(boardService.getBoardById(boardId));
            updatedPost.setId(postId);
            postRepositry.save(updatedPost);
            return true;
        } else {
            return false;
        }
    }
}
