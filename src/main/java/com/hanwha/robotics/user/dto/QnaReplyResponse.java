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
    private String memberId;
    private String adminId;
    private String replyContent;
    private String replyType;
    private String createDt;

    public void setMaskedMemberId() {
        int maskLength = memberId.length() / 2;
        this.memberId = memberId.substring(0, maskLength) + "*".repeat(maskLength);
    }
}
