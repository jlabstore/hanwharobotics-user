package com.hanwha.robotics.user.entity;

import java.time.LocalDateTime;

import com.hanwha.robotics.user.common.enums.MemberLogType;

import lombok.Data;

@Data
public class MemberLog {
    private int logNo;
    private int memberNo;
    private MemberLogType logType;
    private LocalDateTime accessDate;
}
