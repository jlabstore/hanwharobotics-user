package com.hanwha.robotics.user.dto.robot;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RobotCategoryResponse {

  private int categoryNo;
  private String categoryNm;
  private String boardType1;
  private String delYn;
  private Timestamp createDt;
  private Timestamp updateDt;

}
