package com.playdata.pdshared.domain.group.repository;

import com.playdata.pdshared.domain.group.domain.entity.Join;
import com.playdata.pdshared.domain.group.domain.entity.Team;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinRepository extends JpaRepository<Join, Long> {

    Optional<Join> findByTeamAndMember(Team team, Member member);
}
