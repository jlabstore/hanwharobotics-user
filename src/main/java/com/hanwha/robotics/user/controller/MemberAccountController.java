package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberAccountController {

    @Autowired
    private MemberService memberService;

	/**
	 * 마이페이지 회원정보 페이지
	 * @param memberNo
	 * @param model
	 * @return
	 */
    @GetMapping("/profile")
    public String myPage(@AuthenticationPrincipal int memberNo, Model model) {
        MemberResponse memberResponse = memberService.retrieve(memberNo);
        model.addAttribute("memberResponse", memberResponse);
        return "member/my_page";
    }

	/**
	 * 마이페이지 회원정보 수정 페이지
	 * @return
	 */
    @GetMapping("/profile/modify")
    public String modifyPage() {
        return "member/my_page_modify";
    }

	/**
	 * 마이페이지 회원정보 수정
	 * @return
	 */
    @PutMapping("/profile/modify")
    public String modify() {

        return "member/my_page";
    }

    /**
     * 회원 탈퇴 페이지
     * @return
     */
    @GetMapping("/profile/delete")
    public String deleteAccountPage() {
        return "member/delete_account";
    }

    /**
     * 회원 탈퇴
     * @param memberNo
     * @return
     */
    @DeleteMapping("/profile/delete")
    public String deleteAccount(@AuthenticationPrincipal int memberNo) {
        memberService.deleteAccount(memberNo);
        return "main";
    }

    /**
     * 비밀번호 재설정 페이지
     * @return
     */
    @GetMapping("/change/password")
    public String changePasswordPage() {
        return "/member/change_pw";
    }

    /**
     * 비밀번호 재설정
     * @param memberNo
     * @param request
     * @return
     */
    @PutMapping("/change/password")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal int memberNo,
            @RequestBody MemberRequest request
    ) {
        memberService.changePassword(memberNo, request);
        return ResponseEntity.ok().build();
    }

}
