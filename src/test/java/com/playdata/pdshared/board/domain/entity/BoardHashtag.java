package com.playdata.pdshared.board.domain.entity;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class BoardHashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Board board;
    @ManyToOne
    private Hashtag hashtag;
}
