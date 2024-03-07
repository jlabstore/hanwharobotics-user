package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agv-amr")
public class AgvAmrController {

    @GetMapping
    public String products() {
        return "agv_amr/products";
    }

    @GetMapping("/forklift-type")
    public String forkliftType() {
        return "agv_amr/products_detail";
    }


}
