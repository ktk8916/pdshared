package com.playdata.pdshared.domain.filestorage.domain.entity;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity @Table(name = "file_storage")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class FileStorage extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalName;
    private String uuid;
    private String extension;
    private Long volume;
    private String savePath;
    @OneToOne
    private Board board;
}
