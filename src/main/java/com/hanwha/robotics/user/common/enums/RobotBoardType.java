package com.hanwha.robotics.user.common.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RobotBoardType {

  AUTO_SYSTEM("자동화 시스템"),
  ROBOT_CASE("적용 사례"),
  CO_ROBOT("협동로봇"),
  AVG_AMR("AVG_AMR");

  private final String type;

  public static String getName(String boardType2) {
    return Arrays.stream(RobotBoardType.values())
        .filter(robotBoardType -> robotBoardType.name().equalsIgnoreCase(boardType2))
        .map(RobotBoardType::getType)
        .findFirst()
        .orElse("Unknown");
  }

}
