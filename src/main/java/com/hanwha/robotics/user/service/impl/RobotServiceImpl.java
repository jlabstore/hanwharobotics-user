package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.dto.QnaResponse;
import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import com.hanwha.robotics.user.mapper.RobotMapper;
import com.hanwha.robotics.user.service.RobotService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public PageResponse findRobotByCategory2(RobotRequest robotRequest, PageRequest page, String lang) {
    Map<String, Object> map = new HashMap<>();
    map.put("page", page);

    int totalCount = robotMapper.countRobotByCategoryList(map, robotRequest, lang);
    List<RobotResponse> responseList = robotMapper.findRobotByCategory2(robotRequest, page, lang);
    return new PageResponse(responseList, totalCount, page);
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
