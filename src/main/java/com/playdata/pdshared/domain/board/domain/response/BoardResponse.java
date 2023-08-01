package com.playdata.pdshared.domain.board.domain.response;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.BoardHashtag;
import com.playdata.pdshared.domain.board.domain.entity.Comment;
import com.playdata.pdshared.domain.board.domain.entity.ViewType;
import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class BoardResponse{

    private Member member;
    private String title;
    private String content;
    private Long viewCount;
    private Long likeCount;
    private Long downloadCount;
    private ViewType viewType;

    public BoardResponse (Board board){

        this.content=board.getContent();
        this.title=board.getTitle();
        this.member=board.getMember();
        this.viewType=board.getViewType();
        this.likeCount=board.getLikeCount();
        this.viewCount=board.getViewCount();
        this.downloadCount=board.getDownloadCount();

    }
}
