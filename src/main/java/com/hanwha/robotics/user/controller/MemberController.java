package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "contact/login";
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping("/signup")
    public String signup() {
        return "contact/signup";
    }

    /**
     * 회원가입
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public String register(MemberRequest request) {
        memberService.registerMember(request);
        return "main";
    }
}
