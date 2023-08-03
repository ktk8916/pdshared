package com.playdata.pdshared.domain.group.controller;

import com.playdata.pdshared.domain.group.domain.request.TeamRequest;
import com.playdata.pdshared.domain.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public void createTeam(@RequestBody TeamRequest teamRequest){
        // 멤버 아이디를 받아와서 같이 처리해야함
        // 일단은 1L로 고정
        groupService.createTeam(1L, teamRequest);
    }

    @PostMapping("/{teamId}/invite/{memberId}")
    public void invite(
            @PathVariable("teamId") Long teamId,
            @PathVariable("memberId") Long memberId){
        groupService.invite(teamId, memberId);
    }


}
