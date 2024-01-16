package com.hanwha.robotics.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaReplyRequest {
    private int qnaNo;
    private String replyContent;
    private String replyType;
}
