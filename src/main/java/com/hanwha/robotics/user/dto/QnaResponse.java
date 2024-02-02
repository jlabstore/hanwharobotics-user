package com.hanwha.robotics.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaResponse {
    private int qnaNo;
    private int memberNo;
    private String memberId;
    private String maskedMemberId;

    private String title;
    private String content;
    private String emailReceiveYn;
    private String endStatus;
    private String exposureStatus;
    private LocalDate createDt;

    public void setMaskedMemberId() {
        int maskLength = memberId.length() / 2;
        this.memberId = memberId.substring(0, maskLength) + "*".repeat(maskLength);
    }

}
