package com.playdata.pdshared.board.domain.entity;

import com.playdata.pdshared.filestorage.domain.entity.FileStorage;
import com.playdata.pdshared.global.domain.BaseEntity;
import com.playdata.pdshared.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;
    private String title;
    private String content;
    private Long viewCount;
    private Long likeCount;
    private Long downloadCount;
    @Enumerated(EnumType.STRING)
    private ViewType viewType;
    @OneToOne
    private FileStorage fileStorage;
    @OneToMany(mappedBy = "board")
    private List<Comment> comments;
    @OneToMany(mappedBy = "board")
    private List<BoardHashtag> hashtags;
}
