package com.example.dailylog.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> findAll() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardByID (@PathVariable Long id) {
        Board board = boardService.getBoardByID(id);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createBoard (@RequestBody Board board) {
        boardService.createBoard(board);
        return new ResponseEntity<>("Board created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard (@PathVariable Long id) {
        boolean deleted = boardService.deleteBoardById(id);
        if (deleted) {
            return new ResponseEntity<>("Board deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost (@PathVariable Long id,
                                              @RequestBody Board updatedBoard) {
        boolean updated = boardService.updateBoard(id, updatedBoard);
        if (updated) {
            return new ResponseEntity<>("Board updated successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

/*

GET /boards: Get all boards
GET /boards/{id}: Get a board by ID
POST /boards: Create a new board
DELETE /boards/{id}: Delete a board by ID
PUT /boards/{id}: Update a board by ID

 */