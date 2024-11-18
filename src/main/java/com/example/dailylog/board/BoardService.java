package com.example.dailylog.board;

import java.util.List;

public interface BoardService {
    List<Board> findAll();
    void createBoard(Board board);

    Board getBoardByID(Long id);

    boolean deleteBoardById(Long id);

    boolean updateBoard(Long id, Board updatedBoard);
}
