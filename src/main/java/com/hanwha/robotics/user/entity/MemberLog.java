package com.hanwha.robotics.user.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberLog {
    private int logNo;
    private int memberNo;
    private LocalDateTime accessDt;
}
