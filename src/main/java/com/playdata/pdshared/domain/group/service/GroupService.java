package com.playdata.pdshared.domain.group.service;

import com.playdata.pdshared.domain.group.domain.entity.Join;
import com.playdata.pdshared.domain.group.domain.entity.Team;
import com.playdata.pdshared.domain.group.domain.request.TeamRequest;
import com.playdata.pdshared.domain.group.exception.ExistMemberException;
import com.playdata.pdshared.domain.group.exception.TeamNotFoundException;
import com.playdata.pdshared.domain.group.repository.JoinRepository;
import com.playdata.pdshared.domain.group.repository.TeamRepository;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {

    private final TeamRepository teamRepository;
    private final JoinRepository joinRepository;
    public Team findById(Long id){
        return teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    public void createTeam(Long memberId, TeamRequest teamRequest) {
        Member member = Member.builder()
                .id(memberId)
                .build();

        Team team = Team.builder()
                .name(teamRequest.name())
                .owner(member)
                .build();

        teamRepository.save(team);
    }

    public void invite(Long teamId, Long memberId) {
        Team team = findById(teamId);

        Member member = Member.builder()
                .id(memberId)
                .build();

        Optional<Join> join = joinRepository.findByTeamAndMember(team, member);

        if(join.isPresent()) {
            throw new ExistMemberException();

        }

        joinRepository.save(Join.of(team, member));
    }
}
