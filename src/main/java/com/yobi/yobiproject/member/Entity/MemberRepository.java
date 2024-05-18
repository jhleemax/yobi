package com.yobi.yobiproject.member.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //select문 정의
    Member findByUserId(String userId);
    Member findByUserIdAndUserPass(String userId, String userPass);
}