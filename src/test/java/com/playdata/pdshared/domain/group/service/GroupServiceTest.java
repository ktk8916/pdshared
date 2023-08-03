package com.playdata.pdshared.domain.group.service;

import com.playdata.pdshared.domain.group.domain.entity.Join;
import com.playdata.pdshared.domain.group.domain.entity.Team;
import com.playdata.pdshared.domain.group.domain.request.TeamRequest;
import com.playdata.pdshared.domain.group.exception.ExistMemberException;
import com.playdata.pdshared.domain.group.exception.TeamNotFoundException;
import com.playdata.pdshared.domain.group.exception.TeamPermissionException;
import com.playdata.pdshared.domain.group.repository.JoinRepository;
import com.playdata.pdshared.domain.group.repository.TeamRepository;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import com.playdata.pdshared.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupServiceTest {

    @Autowired
    GroupService groupService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    JoinRepository joinRepository;
    Member member;
    Team team;
    String teamName = "테스트용 팀 이름";
    @BeforeEach
    void init(){
        Member newMember = Member.builder().id(1L).build();
        this.member = memberRepository.save(newMember);
        Team newTeam = Team.builder().owner(member).name(teamName).build();
        this.team = teamRepository.save(newTeam);
    }

    @AfterEach
    void clear(){
        memberRepository.deleteAll();
        teamRepository.deleteAll();;
    }

    @Nested
    class 팀_생성{
        @Test
        void 팀_생성_성공(){
            //given
            TeamRequest teamRequest = new TeamRequest(teamName);
            int size = teamRepository.findAll().size();
            //when
            groupService.createTeam(member.getId(), teamRequest);
            //then
            assertThat(teamRepository.findAll()).hasSize(size+1);
        }

        @Test
        void 증복_이름_팀_생성시_예외(){
            //생각해보니까 일단 중복가능했네..
            //given
            //when
            //then
        }
    }
    @Nested
    class 팀_조회{
        @Test
        void 팀_조회_성공(){
            //given
            Long teamId = team.getId();
            //when
            Team findTeam = groupService.findById(teamId);
            //then
            assertThat(findTeam.getName()).isEqualTo(teamName);
        }

        @Test
        void 없는_팀_조회시_예외(){
            //given
            Long teamId = 100000L;
            //when, then
            assertThrows(
                    TeamNotFoundException.class,
                    ()->groupService.findById(teamId));
        }
    }

    @Nested
    class 팀_초대{
        Member newMember = memberRepository
                .save(Member.builder()
                                .id(10L)
                                .build());
        @Test
        void 팀_초대_성공(){
            //given
            int size = joinRepository.findAll().size();

            //when
            groupService.invite(member.getId(), team.getId(), newMember.getId());

            //then
            assertThat(joinRepository.findAll()).hasSize(size+1);
        }

        @Test
        void 팀_초대시_그룹장_아니면_예외(){
            //given
            Member common = Member.builder().id(20L).build();
            //when, then
            assertThrows(
                    TeamPermissionException.class,
                    ()->groupService.invite(common.getId(), team.getId(), newMember.getId())
            );
        }

        @Test
        void 팀_중복멤버_초대시_예외(){
            //given
            groupService.invite(member.getId(), team.getId(), newMember.getId());
            //when, then
            assertThrows(
                    ExistMemberException.class,
                    ()-> groupService.invite(member.getId(), team.getId(), newMember.getId())
            );
        }
    }
}