package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.common.enums.RobotBoardType;
import com.hanwha.robotics.user.dto.robot.RobotCategoryResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RobotCategoryMapper {

  List<RobotCategoryResponse> findRobotCategory(RobotRequest request);

  List<RobotCategoryResponse> findRobotCategory(String boardType1);

}
