package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/robot")
public class RobotController {

    @GetMapping
    public String robot() {
        return "robot/robots";
    }

    @GetMapping("/detail")
    public String robotDetail() {
        return "robot/robots_detail";
    }


}
