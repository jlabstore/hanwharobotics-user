package com.hanwha.robotics.user.dto.robot;

import lombok.Data;

@Data
public class RobotRequest {

  private int categoryNo;
  private String boardType1;
  private String boardType2;
  private String robotNm;
  private String lang;

}
