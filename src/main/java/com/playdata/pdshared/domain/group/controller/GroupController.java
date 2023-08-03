package com.playdata.pdshared.domain.group.controller;

import com.playdata.pdshared.config.service.OauthService;
import com.playdata.pdshared.config.token.TokenService;
import com.playdata.pdshared.domain.group.domain.request.TeamRequest;
import com.playdata.pdshared.domain.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
public class GroupController {

    private final GroupService groupService;
    private final TokenService tokenService;

    @PostMapping
    public void createTeam(
            @RequestHeader("Authorization") String token,
            @RequestBody TeamRequest teamRequest){
        Long memberId = tokenService.getMemberIdByToken(token);
        groupService.createTeam(memberId, teamRequest);
    }

    @PostMapping("/{teamId}/invite/{memberId}")
    public void invite(
            @RequestHeader("Authorization") String token,
            @PathVariable("teamId") Long teamId,
            @PathVariable("memberId") Long memberId){
        Long ownerId = tokenService.getMemberIdByToken(token);
        groupService.invite(ownerId, teamId, memberId);
    }


}
