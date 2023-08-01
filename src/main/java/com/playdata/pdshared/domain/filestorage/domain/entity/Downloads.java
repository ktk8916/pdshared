package com.playdata.pdshared.domain.filestorage.domain.entity;

import com.playdata.pdshared.domain.board.domain.entity.Board;
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
    private FileStorage fileStorage;

    public static Downloads of(Member member, FileStorage fileStorage){
        Downloads downloads = new Downloads();
        downloads.member = member;
        downloads.fileStorage = fileStorage;
        return downloads;
    }
}
