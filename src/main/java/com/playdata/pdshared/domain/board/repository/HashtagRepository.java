package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {

    Hashtag findByName (String name);
}
