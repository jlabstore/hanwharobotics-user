package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import java.util.List;

public interface RobotService {

  List<RobotResponse> findRobot(String boardType1, String boardType2, Integer categoryNo, String lang);

  RobotResponse findRobotByRobotNo(int robotNo);

  List<RobotFileResponse> findRobotFileByRoboyNo(int robotNo);
}
