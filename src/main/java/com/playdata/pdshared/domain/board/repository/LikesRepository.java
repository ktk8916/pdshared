package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import com.playdata.pdshared.domain.member.domain.entity.Member;


public interface LikesRepository extends JpaRepository<Likes,Long> {
    Likes findByMemberAndBoard(Member member, Board board);
}
