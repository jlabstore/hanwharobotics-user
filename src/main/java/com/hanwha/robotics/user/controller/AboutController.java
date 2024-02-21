package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/about")
public class AboutController {

    /**
     * about 페이지
     * @return
     */
    @GetMapping("")
    public String about(@RequestParam String lang){
        if ("en".equals(lang)) {
            // 영문페이지
            return "about/about";
		} else {
            // 국문페이지
            return "about/aboutKr";
		}
    }

    // /**
    //  * about 임시 한글 페이지
    //  */
    // @GetMapping("/about")
    // public String aboutKr(){
    //     return "about/aboutKr";
    // }

    /**
     * 경영방침 페이지
     */
    @GetMapping("/managementPolicy")
    public String managementPolicy() {
        return "about/management_policy";
    }
}
