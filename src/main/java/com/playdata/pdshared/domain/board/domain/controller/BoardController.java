package com.playdata.pdshared.domain.board.domain.controller;


import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.request.BoardRequest;
import com.playdata.pdshared.domain.board.domain.response.BoardResponse;
import com.playdata.pdshared.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //일단은 JSON으로 BoardRequest를 받기로햇지만,
    //프론트가 나오면 @RequestParam으로 고쳐야한다.
    @PostMapping
    public String save(
            @RequestHeader("Authorization") String token,
            @RequestBody BoardRequest boardRequest){

        //여기서 token까서 member를 불러온뒤,
        // boardRequest에 넣어서 서비스로 보내야함

        boardService.insert(boardRequest);

        return "save 완료";
    }

    @GetMapping("/{keyword}")
    public BoardResponse find(
            @RequestHeader("Authorization") String token,
            @PathVariable String keyword){

        return boardService.findByContent(keyword,token);
    }

    @PostMapping("/like")
    public String like(
            @RequestHeader("Authorization") String token,
            @RequestParam Long boardId){

         boardService.like(boardId,token);

         return "좋아요 완료";
    }

    @PostMapping("/comment")
    public String comment(
            @RequestHeader("Authorization") String token,
            @RequestParam Long boardId,
            @RequestParam String content
            ) {

        boardService.insertComment(boardId,content,token);

        return "댓글 달기 완료";
    }

    //이건 그룹(Team) 내에서 공유할 게시글 등록
    // Request로 묶을까 생각중
    @PostMapping("/group")
    public String group(
            @RequestParam List<Long> boardList,
            @RequestParam Long groupId
    ){
        boardService.group(boardList,groupId);

        return "그룹 내 게시글 등록 완료";
    }

}
