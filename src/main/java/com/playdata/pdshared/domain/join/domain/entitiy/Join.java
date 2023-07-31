package com.playdata.pdshared.domain.join.domain.entitiy;

import com.playdata.pdshared.domain.group.domain.entity.Team;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "joins")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Join {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Team team;
    @ManyToOne
    Member member;
}
