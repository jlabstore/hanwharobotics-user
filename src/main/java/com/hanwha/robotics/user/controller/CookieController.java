package com.hanwha.robotics.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CookieController {
   @GetMapping("/cookie")
   public String cookie() {
       return "cookie/cookie";
   }
}
