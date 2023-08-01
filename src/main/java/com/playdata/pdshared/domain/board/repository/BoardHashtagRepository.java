package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.BoardHashtag;
import com.playdata.pdshared.domain.board.domain.entity.Hashtag;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHashtagRepository extends JpaRepository<BoardHashtag,Long> {

    BoardHashtag findByBoardAndHashtag (Board board,Hashtag hashtag);
}
