package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaResponse {
    private int qnaNo;
    private int memberNo;
    private String memberId;
    //    private String maskedMemberId;
    private String email;
    private String title;
    private String content;
    private String qnaType1Nm;
    private String qnaType2Nm;
    private String emailReceiveYn;
    private String endStatus;
    private String exposureStatus;
    private String fixedStatus;
    private LocalDate createDt;

    public void setMaskedMemberId() {
        int maskLength = memberId.length() / 2;
        this.memberId = memberId.substring(0, maskLength) + "*".repeat(maskLength);
    }

    public static QnaResponse of(Qna qna){
        return QnaResponse.builder()
                .qnaNo(qna.getQnaNo())
                .memberNo(qna.getMemberNo())
                .memberId(qna.getMemberId())
                .email(qna.getEmail())
                .title(qna.getTitle())
                .content(qna.getContent())
                .qnaType1Nm(qna.getQnaType1Cd())
                .qnaType2Nm(qna.getQnaType2Cd())
                .emailReceiveYn(qna.getEmailReceiveYn())
                .endStatus(qna.getEndStatus())
                .exposureStatus(qna.getExposureStatus())
                .fixedStatus(qna.getFixedStatus())
//                .createDt(LocalDate.from(qna.getCreateDt()))
                .build();
    }


}
