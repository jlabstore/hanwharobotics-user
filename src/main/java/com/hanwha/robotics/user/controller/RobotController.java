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

    @GetMapping("/hcr-3a")
    public String robotHcr3a() {
        return "robot/robots_detail_hcr_3a";
    }

    @GetMapping("/hcr-5a")
    public String robotHcr5a() {
        return "robot/robots_detail_hcr_5a";
    }

    @GetMapping("/solutions")
    public String solutions() {
        return "robot/solutions";
    }



}
