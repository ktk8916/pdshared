package com.playdata.pdshared.domain.board.domain.entity;

import com.playdata.pdshared.domain.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Downloads {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Board board;
}
