package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    /**
     * about 페이지
     * @return
     */
    @GetMapping("")
    public String about(){
        return "about/about";
    }

    /**
     * 경영방침 페이지
     */
    @GetMapping("/managementPolicy")
    public String managementPolicy() {
        return "about/management_policy";
    }
}
