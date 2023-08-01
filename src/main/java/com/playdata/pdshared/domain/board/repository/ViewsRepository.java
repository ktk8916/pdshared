package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Views;
import org.springframework.data.jpa.repository.JpaRepository;
import com.playdata.pdshared.domain.member.domain.entity.Member;

public interface ViewsRepository extends JpaRepository<Views,Long> {
    Views findByMemberAndBoard(Member member,Board board);
}
