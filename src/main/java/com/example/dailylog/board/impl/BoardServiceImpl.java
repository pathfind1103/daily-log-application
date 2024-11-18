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
    private Long nextId = 1L;

    public BoardServiceImpl(BoardRepositry boardRepositry) {
        this.boardRepositry = boardRepositry;
    }

    @Override
    public List<Board> findAll() {
        return (List<Board>) boardRepositry.findAll();
    }

    @Override
    public void createBoard(Board board) {
        board.setId(nextId++);
        boardRepositry.save(board);
    }

    @Override
    public Board getBoardByID(Long id) {
        return boardRepositry.findById(id).orElse(null);
    }

    @Override
    public boolean deleteBoardById(Long id) {
        try {
            boardRepositry.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateBoard(Long id, Board updatedBoard) {
        Optional<Board> boardOptional = boardRepositry.findById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setId(updatedBoard.getId());
            board.setName(updatedBoard.getName());
            board.setDescription(updatedBoard.getDescription());
            boardRepositry.save(board);
            return true;
        }
        return false;
    }
}
