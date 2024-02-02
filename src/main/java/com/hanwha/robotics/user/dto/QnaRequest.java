package com.hanwha.robotics.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaRequest {
    public int qnaNo;
    public int memberNo;
    public String memberId;
    public String email;
    public String title;
    public String content;
    public String emailReceiveYn;
    public String exposureStatus;
}
