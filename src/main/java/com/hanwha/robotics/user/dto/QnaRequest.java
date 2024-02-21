package com.hanwha.robotics.user.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hanwha.robotics.user.entity.Qna;
import com.hanwha.robotics.user.entity.QnaRobot;

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
    public String qnaType1Cd;
    public String qnaType2Cd;
    public String emailReceiveYn;

    private List<QnaRobot> qnaRobots;

    @Getter
    private ArrayList<MultipartFile> files = new ArrayList<>();

    public Qna toEntity() {
        return Qna.builder()
                .qnaNo(qnaNo)
                .memberNo(memberNo)
                .memberId(memberId)
                .email(email)
                .title(title)
                .content(content)
                .qnaType1Cd(qnaType1Cd)
                .qnaType2Cd(qnaType2Cd)
                .emailReceiveYn(emailReceiveYn)
                .build();
    }

}
