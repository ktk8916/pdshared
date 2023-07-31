package com.playdata.pdshared.domain.board.repository;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.domain.board.domain.entity.Downloads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadsRepository extends JpaRepository<Downloads,Long> {

}
