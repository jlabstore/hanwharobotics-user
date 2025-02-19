package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import java.util.List;

public interface RobotService {

  List<RobotResponse> findRobot(String boardType1, String boardType2, Integer categoryNo, String lang);

  List<RobotResponse> findRobotByCategory(RobotRequest robotRequest);

  PageResponse findRobotByCategory2(RobotRequest robotRequest, PageRequest page, String lang);





  RobotResponse findRobotByRobotNo(int robotNo);

  List<RobotFileResponse> findRobotFileByRoboyNo(int robotNo);

  List<RobotFileResponse> findRobotFileByRoboyNoNoneThumnail(int robotNo);

  
}
