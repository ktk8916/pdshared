package com.playdata.pdshared.domain.filestorage.domain.entity;

import com.playdata.pdshared.domain.board.domain.entity.Board;
import com.playdata.pdshared.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table(name = "file_storage")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
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

    public static FileStorage from(MultipartFile file){
        FileStorage fileStorage = new FileStorage();
        String filename = file.getOriginalFilename();
        fileStorage.originalName = filename;
        fileStorage.uuid = UUID.randomUUID().toString();
        fileStorage.extension = filename.substring(filename.lastIndexOf("."));
        fileStorage.volume = file.getSize();
        fileStorage.savePath = fileStorage.uuid + fileStorage.extension;
        return fileStorage;
    }
}
