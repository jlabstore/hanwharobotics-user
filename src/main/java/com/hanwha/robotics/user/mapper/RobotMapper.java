package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RobotMapper {

  List<RobotResponse> findRobot(String boardType1, String boardType2, Integer categoryNo, String lang);

  RobotResponse findRobotByRobotNo(int robotNo);

  List<RobotFileResponse> findRobotFileByRoboyNo(int robotNo);

  List<RobotResponse> findRobotByCategory(RobotRequest robotRequest);

  int countRobotByCategoryList(Map<String, Object> map, RobotRequest request, String lang);

  List<RobotResponse> findRobotByCategory2(RobotRequest request, PageRequest page, String lang);
}
