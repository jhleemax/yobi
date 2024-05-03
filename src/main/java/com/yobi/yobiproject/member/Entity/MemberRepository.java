package com.yobi.yobiproject.member.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //select문 정의
    Member findByUserId(String userId);

}