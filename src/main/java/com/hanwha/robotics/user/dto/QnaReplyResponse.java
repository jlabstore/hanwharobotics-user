package com.hanwha.robotics.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaReplyResponse {
    private int replyNo;
    private int qnaNo;
    private String adminId;
    private String replyContent;
    private String replyType;
    private String createDt;
}
