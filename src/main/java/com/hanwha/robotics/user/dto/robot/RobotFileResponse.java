package com.hanwha.robotics.user.dto.robot;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RobotFileResponse {

  private String fileNo;
  private String fileNm;
  private String filePath;
  private String thumnailYn;

}
