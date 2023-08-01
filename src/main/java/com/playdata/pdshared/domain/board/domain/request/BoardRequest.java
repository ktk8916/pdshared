package com.playdata.pdshared.domain.board.domain.request;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.BoardHashtag;
import com.playdata.pdshared.domain.board.domain.entity.Hashtag;
import com.playdata.pdshared.domain.board.domain.entity.ViewType;
import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;

import java.util.List;

public record BoardRequest(

        String content,
        String title,
        ViewType viewType,
        List<Hashtag> hashtags,
        FileStorage fileStorage
) {
    public Board toEntity(){

        return Board.builder()
                .content(content)
                .title(title)
                .viewType(viewType)
//                .hashtags(hashtags)
                .fileStorage(fileStorage)
                .likeCount(0L)
                .downloadCount(0L)
                .viewCount(0L)
                .build();
    }
}
