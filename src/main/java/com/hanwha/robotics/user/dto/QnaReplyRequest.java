package com.hanwha.robotics.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QnaReplyRequest {
    public String replyContent;
}
