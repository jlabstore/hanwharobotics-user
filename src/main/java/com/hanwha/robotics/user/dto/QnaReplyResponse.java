package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.QnaReply;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaReplyResponse {
    private int replyNo;
    private int qnaNo;

    private int memberNo;
    private String memberId;
    private String adminId;
    private String replyContent;
    private String replyType;
    private String createDt;

    public void setMaskedMemberId() {
        int maskLength = memberId.length() / 2;
        this.memberId = memberId.substring(0, maskLength) + "*".repeat(maskLength);
    }

//    public static QnaReplyResponse from(QnaReply qnaReply) {
//        return QnaReplyResponse.builder()
//                .replyNo(qnaReply.getReplyNo())
//                .qnaNo(qnaReply.getQnaNo())
//                .build();
//    }
}
