package com.playdata.pdshared.domain.board.service;

import com.playdata.pdshared.domain.board.domain.entity.*;
import com.playdata.pdshared.domain.board.domain.request.BoardRequest;
import com.playdata.pdshared.domain.board.domain.response.BoardResponse;
import com.playdata.pdshared.domain.board.repository.*;
import com.playdata.pdshared.domain.filestorage.service.FileStorageService;
import com.playdata.pdshared.domain.group.domain.entity.Team;
import com.playdata.pdshared.domain.group.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final HashtagRepository hashtagRepository;
    private final BoardHashtagRepository boardHashtagRepository;
    private final ViewsRepository viewsRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;

    private final BoardTeamRepository boardTeamRepository;
    private final TeamRepository teamRepository;

    private final FileStorageService fileStorageService;

    public void insert(BoardRequest request, MultipartFile file){

        //입력이 필요한부분
        //member는 토큰 까면 될듯?
        //member content,title,viewType,hashtags,fileStorage


        //Board 입력을 받을때, 해쉬태그도 입력이 필요함
        List<Hashtag> hashtags = request.hashtags();

        if(hashtags!=null){
            for(Hashtag h : hashtags){
                Hashtag byName = hashtagRepository.findByName(h.getName());

                if(byName==null){
                    hashtagRepository.save(h);
                }
            }
        }



        //해쉬태그 입력이 끝나면, Board-HashTag 연결상태 입력도 해야함
        Board save = boardRepository.save(request.toEntity());

        if(hashtags!=null){
            for(Hashtag h : hashtags){

                BoardHashtag boardHashtag = new BoardHashtag(null,save,h);
                boardHashtagRepository.save(boardHashtag);
            }
        }


        //파일저장 로직


        fileStorageService.upload(save.getId(),file);


    }

    // Board 조회로직
    public BoardResponse findByTitle(String keyword, String token){

        //Board를 조회하면  조회수 카운트를 하나 늘려야함.
        //중복체크도하고
        Board board = boardRepository.findByTitleContains(keyword);

        //여기 jwt토큰필요
        // 토큰을 벗겨낸뒤, Member를 넣어야함
        Views views = viewsRepository.findByMemberAndBoard(null, board);

        if(views==null){

            board.addViewCount();
            // 여기 member에도 토큰을 집어넣어야함
            viewsRepository.save(new Views(null,null,board));
        }

        return new BoardResponse(board);
    }


    public void like(Long boardId, String token) {

        Board board = boardRepository.findById(boardId).get();

        //중복체크  여기 토큰 필요함
        Likes likes = likesRepository.findByMemberAndBoard(null, board);

        if(likes==null){
            board.addLikeCount();
            //토큰 필요함
            Likes build = Likes.builder()
                    .board(board)
                    .member(null)
                    .build();

            likesRepository.save(build);
        }


    }

    public void insertComment(Long boardId, String content, String token) {


        Board board = boardRepository.findById(boardId).get();

        //토큰 필요함
        Comment comment = Comment.builder()
                .board(board)
                .content(content)
                .member(null).build();

        board.getComments().add(comment);

        commentRepository.save(comment);
    }

    public void group(List<Long> boardList, Long groupId) {

        Team team = teamRepository.findById(groupId).get();

        for(Long boardId: boardList){

            Board board = boardRepository.findById(boardId).get();

            BoardTeam boardTeam = BoardTeam.builder()
                    .board(board)
                    .team(team)
                    .build();

            boardTeamRepository.save(boardTeam);
        }

    }
}