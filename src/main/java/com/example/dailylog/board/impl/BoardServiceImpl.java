package com.example.dailylog.board.impl;

import com.example.dailylog.board.Board;
import com.example.dailylog.board.BoardRepositry;
import com.example.dailylog.board.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    BoardRepositry boardRepositry;

    public BoardServiceImpl(BoardRepositry boardRepositry) {
        this.boardRepositry = boardRepositry;
    }

    @Override
    public List<Board> getAllBoards() {
        return (List<Board>) boardRepositry.findAll();
    }

    @Override
    public void createBoard(Board board) {
        boardRepositry.save(board);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepositry.findById(id).orElse(null);
    }

    @Override
    public boolean deleteBoardById(Long id) {
        if (boardRepositry.existsById(id)) {
            boardRepositry.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBoard(Long id, Board updatedBoard) {
        Optional<Board> boardOptional = boardRepositry.findById(id);
        if (boardOptional.isPresent()) {
            Board boardToUpdate = boardOptional.get();
            boardToUpdate.setName(updatedBoard.getName());
            boardToUpdate.setDescription(updatedBoard.getDescription());
            boardToUpdate.setPosts(boardToUpdate.getPosts());
            boardRepositry.save(boardToUpdate);
            return true;
        }
        return false;
    }
}
