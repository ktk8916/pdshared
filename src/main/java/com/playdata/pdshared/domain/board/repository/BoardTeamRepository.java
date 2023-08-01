package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.BoardTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTeamRepository extends JpaRepository<BoardTeam,Long> {

}
