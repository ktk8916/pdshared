package com.playdata.pdshared.board.domain.entity;

import com.playdata.pdshared.BaseEntity;
import com.playdata.pdshared.Member;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Board board;

}
