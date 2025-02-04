package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.dto.PageRequest;
import com.hanwha.robotics.user.common.dto.PageResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.enums.RobotBoardType;
import com.hanwha.robotics.user.common.utils.CommonUtil;
import com.hanwha.robotics.user.dto.robot.RobotCategoryResponse;
import com.hanwha.robotics.user.dto.robot.RobotFileResponse;
import com.hanwha.robotics.user.dto.robot.RobotRequest;
import com.hanwha.robotics.user.dto.robot.RobotResponse;
import com.hanwha.robotics.user.service.RobotCategoryService;
import com.hanwha.robotics.user.service.RobotService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cobot")
@RequiredArgsConstructor
@Slf4j
public class CobotController {

  private final RobotCategoryService robotCategoryService;

  private final RobotService robotService;

  private final CommonUtil commonUtil;

  @GetMapping("/modulesystem")
  public String modulesystem(
      @RequestParam(value = "categoryNo", required = false) Integer categoryNo,
      Model model,
      HttpServletRequest request
  ) {
    log.info("여기 들어왔나 {}", categoryNo);
    String lang = commonUtil.getCookieLang(request);
    RobotRequest robotRequest = RobotRequest.builder()
        .lang(lang)
        .boardType1(RobotBoardType.AUTO_SYSTEM.name())
        .boardType2(RobotBoardType.CO_ROBOT.name())
        .categoryNo(categoryNo)
        .build();
    List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory(robotRequest);
    List<RobotResponse> robotResponseList = robotService.findRobotByCategory(robotRequest);
    model.addAttribute("robotCategory", robotCategory);
    model.addAttribute("robotResponseList", robotResponseList);
    return "cobot/modulesystem_list";
  }

  @GetMapping("/modulesystem/{robotNo}")
  public String modulesystemDetail(
      @PathVariable int robotNo,
      Model model
  ) {
    List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory2(RobotBoardType.AUTO_SYSTEM.name());
    RobotResponse robotResponse = robotService.findRobotByRobotNo(robotNo);
    List<RobotFileResponse> robotFileList = robotService.findRobotFileByRoboyNo(robotNo);
    model.addAttribute("robotCategory", robotCategory);
    model.addAttribute("robotResponse", robotResponse);
    model.addAttribute("robotFileList", robotFileList);
    return "cobot/modulesystem_view";
  }

  @GetMapping("/case-studies")
  public String caseStudies(
      @RequestParam(value = "categoryNo", required = false) Integer categoryNo,
      Model model,
      HttpServletRequest request
  ) {
    String lang = commonUtil.getCookieLang(request);
    List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory2(RobotBoardType.ROBOT_CASE.name());
    List<RobotResponse> robotResponseList = robotService.findRobot(RobotBoardType.ROBOT_CASE.name(), RobotBoardType.CO_ROBOT.name(), categoryNo, lang);
    model.addAttribute("robotCategory", robotCategory);
    model.addAttribute("robotResponseList", robotResponseList);
    return "cobot/case_studies_list";
  }

  @GetMapping("/case-studies/{robotNo}")
  public String caseDetail(
      @PathVariable int robotNo,
      Model model
  ) {
    List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory2(RobotBoardType.ROBOT_CASE.name());
    RobotResponse robotResponse = robotService.findRobotByRobotNo(robotNo);
    List<RobotFileResponse> robotFileList = robotService.findRobotFileByRoboyNo(robotNo);
    model.addAttribute("robotCategory", robotCategory);
    model.addAttribute("robotResponse", robotResponse);
    model.addAttribute("robotFileList", robotFileList);
    return "cobot/case_studies_view";
  }

//  @PostMapping("/category")
//  public ResponseEntity<Object> findCategory(@RequestBody RobotRequest request) {
//    List<RobotCategoryResponse> robotCategory = robotCategoryService.findRobotCategory(request);
//    return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), robotCategory));
//  }

//  @PostMapping("/list")
//  public ResponseEntity<Object> findRobotList(HttpServletRequest request) {
//    String lang = commonUtil.getCookieLang(request);
////    List<RobotResponse> robotResponseList = robotService.findRobot(lang);
//    return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name(), robotResponseList));
//  }











}
