package com.example.dailylog.board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();
    void createBoard(Board board);

    Board getBoardById(Long id);

    boolean deleteBoardById(Long id);

    boolean updateBoard(Long id, Board updatedBoard);
}
