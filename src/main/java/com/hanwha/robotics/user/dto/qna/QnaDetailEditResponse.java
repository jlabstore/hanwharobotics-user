package com.hanwha.robotics.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.robotics.user.entity.QnaRobot;
import com.hanwha.robotics.user.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDetailEditResponse {
    private QnaResponse qnaDetail;
    private List<QnaRobot> qnaRobots;
    private List<UploadFile> qnaFiles;



}
