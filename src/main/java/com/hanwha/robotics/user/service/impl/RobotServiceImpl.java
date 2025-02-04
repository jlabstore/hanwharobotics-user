package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import com.hanwha.robotics.user.mapper.RobotMapper;
import com.hanwha.robotics.user.service.RobotService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {

  private final RobotMapper robotMapper;

  @Override
  public List<RobotResponse> findRobot(String boardType1, String boardType2, Integer categoryNo, String lang) {
    return robotMapper.findRobot(boardType1, boardType2, categoryNo, lang);
  }

  @Override
  public List<RobotResponse> findRobotByCategory(RobotRequest robotRequest) {
    return robotMapper.findRobotByCategory(robotRequest);
  }

  @Override
  public RobotResponse findRobotByRobotNo(int robotNo) {
    return robotMapper.findRobotByRobotNo(robotNo);
  }

  @Override
  public List<RobotFileResponse> findRobotFileByRoboyNo(int robotNo) {
    return robotMapper.findRobotFileByRoboyNo(robotNo);
  }


}
