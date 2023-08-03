package com.playdata.pdshared.domain.group.domain.request;

import com.playdata.pdshared.domain.group.domain.entity.Team;

public record TeamRequest(String name) {

    public Team toEntity(){
        return Team.builder()
                .name(name)
                .build();
    }
}
