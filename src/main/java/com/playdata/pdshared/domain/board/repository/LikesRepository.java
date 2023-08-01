package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Likes;
import com.playdata.pdshared.domain.board.domain.entity.Views;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {

    Likes findByMemberAndBoard(Member member, Board board);
}
