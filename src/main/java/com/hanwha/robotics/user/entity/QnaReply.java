package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QnaReply {
    private int replyNO;
    private int qnaNo;
    private String adminName;
    private String replyContent;
    private String replyType;
    private LocalDateTime createDt;
}
