package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Qna {
    private int qnaNo;
    private int memberNo;
    private String memberId;
    private String email;
    private String title;
    private String content;
    private String qnaType1Cd;
    private String qnaType2Cd;
    private String emailReceiveYn;
    private String endStatus;
    private String exposureStatus;
    private LocalDateTime createDt;
}
