package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Member {
    private int memberNo;
    private String memberId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createDt;
    private String acceptYn;
    private String useYn;
}
