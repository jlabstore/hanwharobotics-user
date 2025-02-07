package com.hanwha.robotics.user.dto.robot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RobotRequest {

  private Integer categoryNo;
  private String boardType1;
  private String boardType2;
  private String robotNm;
  private String lang;

}
