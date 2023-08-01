package com.playdata.pdshared.domain.board.service;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Comment;
import com.playdata.pdshared.domain.board.domain.entity.Likes;
import com.playdata.pdshared.domain.board.domain.entity.ViewType;
import com.playdata.pdshared.domain.board.domain.request.BoardRequest;
import com.playdata.pdshared.domain.board.domain.response.BoardResponse;
import com.playdata.pdshared.domain.board.repository.BoardRepository;
import com.playdata.pdshared.domain.board.repository.CommentRepository;
import com.playdata.pdshared.domain.board.repository.LikesRepository;
import com.playdata.pdshared.domain.board.repository.ViewsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ViewsRepository viewsRepository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    CommentRepository commentRepository;
    @Test
    void insert() {
        //given
        BoardRequest boardRequest = new BoardRequest("내용1","제목", ViewType.PUBLIC,null,null);
        //when

        boardService.insert(boardRequest);

        //then

        Board board = boardRepository.findById(1L).get();
        assertThat(boardRequest.title()).isEqualTo(board.getTitle());

    }

    @Test
    void findByContent() {
        //given
        BoardRequest boardRequest = new BoardRequest("내용","뉴진스노래", ViewType.PUBLIC,null,null);
        boardService.insert(boardRequest);

        //when
        BoardResponse 뉴진스 = boardService.findByTitle("뉴진스", null);
        //then

        assertThat(boardRequest.title()).isEqualTo(뉴진스.getTitle());
        assertThat(뉴진스.getViewCount()).isEqualTo(1L);



    }

    @Test
    void like() {
        //given
        BoardRequest boardRequest = new BoardRequest("내용","고양이사진", ViewType.PUBLIC,null,null);
        boardService.insert(boardRequest);

        //when
        boardService.like(1L,null);

        //then
        Board board = boardRepository.findById(1L).get();
        List<Likes> all = likesRepository.findAll();
        assertThat(board.getLikeCount()).isEqualTo(1);
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void insertComment() {
//        //given
//        BoardRequest boardRequest = new BoardRequest("내용","게임", ViewType.PUBLIC,null,null);
//        boardService.insert(boardRequest);
//
//        //when
//        boardService.insertComment(1L,"와 이게임 재밌음",null);
//        //then
//        List<Comment> 재밌음 = commentRepository.findAllByContentContains("재밌음");
//
//        assertThat(재밌음.size()).isEqualTo(1);
    }

    @Test
    void group() {
    }
}