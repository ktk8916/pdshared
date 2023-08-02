package com.playdata.pdshared.domain.member.repository;

import com.playdata.pdshared.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByProviderId(String providerId);
}
