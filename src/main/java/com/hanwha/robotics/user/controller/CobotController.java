package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cobot")
public class CobotController {

  @GetMapping("/case-studies")
  public String caseStudies() {
    return "cobot/case_studies_list";
  }

  @GetMapping("/modulesystem")
  public String modulesystem() {
    return "cobot/modulesystem_list";
  }


}
