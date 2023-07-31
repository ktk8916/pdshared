package com.playdata.pdshared.group.domain.entity;

import com.playdata.pdshared.global.domain.BaseEntity;
import com.playdata.pdshared.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member owner;
    private String name;
    private Long storageUsage;

}
