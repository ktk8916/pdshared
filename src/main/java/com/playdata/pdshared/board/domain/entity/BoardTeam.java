package com.playdata.pdshared.board.domain.entity;

import com.playdata.pdshared.group.domain.entity.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class BoardTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Board board;
    @ManyToOne
    private Team team;
}
