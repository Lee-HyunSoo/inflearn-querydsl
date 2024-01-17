package com.jpa.querydsl.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {

    // 회원명, 팀명, 나이 (ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer ageGoe; // 크거나 같다.
    private Integer ageLoe; // 작거나 같다.
}
