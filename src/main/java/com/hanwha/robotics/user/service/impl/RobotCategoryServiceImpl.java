package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.robot.RobotCategoryResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.mapper.RobotCategoryMapper;
import com.hanwha.robotics.user.service.RobotCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotCategoryServiceImpl implements RobotCategoryService {

  private final RobotCategoryMapper robotCategoryMapper;

  public List<RobotCategoryResponse> findRobotCategory(RobotRequest request) {
    return robotCategoryMapper.findRobotCategory(request);
  }

  public List<RobotCategoryResponse> findRobotCategory2(String request) {
    return robotCategoryMapper.findRobotCategory(request);
  }


}
