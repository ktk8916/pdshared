package com.playdata.pdshared.domain.group.repository;

import com.playdata.pdshared.domain.group.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
