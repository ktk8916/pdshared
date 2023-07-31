package com.playdata.pdshared.filestorage.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "filestorage")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class FileStorage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
