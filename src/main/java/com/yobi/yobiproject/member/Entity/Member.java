package com.yobi.yobiproject.member.Entity;

import com.yobi.yobiproject.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "userid")
    private String userId;

    @Column(nullable = false, name = "userpass")
    private String userPass;

    @CreationTimestamp
    @Column(nullable = false, name = "joindate")
    private LocalDateTime joinDate;

    @Column(nullable = false, name = "username")
    private String username;

    @Builder
    public static Member toMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setUserId(memberDTO.getUserId());
        member.setUserPass(memberDTO.getUserPass());
        member.setUsername(memberDTO.getUsername());
        return member;
    }
}
