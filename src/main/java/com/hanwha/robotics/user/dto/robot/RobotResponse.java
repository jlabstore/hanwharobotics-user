package com.hanwha.robotics.user.dto.robot;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RobotResponse {

  private int robotNo;
  private int categoryNo;
  private String categoryNm;
  private String boardType1;
  private String boardType2;
  private String robotNm;
  private String lang;
  private String videoUrl;
  private String description;
  private String applicationInfo;
  private String scenario;
  private String productSpac;
  private String thumbnailUrl;
  private Timestamp createDt;
  private Timestamp updateDt;


}
