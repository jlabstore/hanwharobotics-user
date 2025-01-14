package com.hanwha.robotics.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaRobot {
    private int qnaRobotNo;
    private int qnaNo;
    private String modelCd;
    private String serialNo;
    private String swVersion;
    private String applicationCd;
}
