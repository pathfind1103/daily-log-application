package com.example.dailylog.post;

import com.example.dailylog.board.Board;
import com.example.dailylog.tag.Tag;
import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Table(name = "post_table")
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Post() {
    }

    public Post(Long id, String title, String content, Date createdAt, Date updatedAt, Board board, Tag tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.board = board;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
