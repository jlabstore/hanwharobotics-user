package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.robot.RobotCategoryResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import java.util.List;

public interface RobotCategoryService {

  List<RobotCategoryResponse> findRobotCategory(RobotRequest request);

  List<RobotCategoryResponse> findRobotCategory2(String boardType1);


}
