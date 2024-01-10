package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Qna {
    private int qnaNo;
    private int memberNo;
    private String title;
    private String content;
    private LocalDateTime createDt;
}
