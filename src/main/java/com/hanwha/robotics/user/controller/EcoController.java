package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eco")
public class EcoController {

    @GetMapping("/main")
    public String ecoSystemMain() {
        return "eco/ecosystem_main";
    }

    @GetMapping("/ecosystem-1")
    public String ecoSystem1() {
        return "eco/ecosystem_template_01";
    }

    @GetMapping("/ecosystem-2")
    public String ecoSystem2() {
        return "eco/ecosystem_template_02";
    }

    @GetMapping("/ecosystem-3")
    public String ecoSystem3() {
        return "eco/ecosystem_template_03";
    }

    @GetMapping("/ecosystem-4")
    public String ecoSystem4() {
        return "eco/ecosystem_template_04";
    }

    @GetMapping("/ecosystem-5")
    public String ecoSystem5() {
        return "eco/ecosystem_template_05";
    }

    @GetMapping("/ecosystem-6")
    public String ecoSystem6() {
        return "eco/ecosystem_template_06";
    }

    @GetMapping("/ecosystem-7")
    public String ecoSystem7() {
        return "eco/ecosystem_template_07";
    }

}
