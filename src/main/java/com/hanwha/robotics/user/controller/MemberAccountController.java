package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.service.CodeService;
import com.hanwha.robotics.user.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberAccountController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private MailUtil mailUtil;

	/**
	 * 회원정보 페이지
	 * @param memberNo
	 * @param model
	 * @return
	 */
    @GetMapping("/profile")
    public String myPage(@AuthenticationPrincipal int memberNo, Model model) {
        MemberResponse response = memberService.retrieve(memberNo);
        model.addAttribute("memberResponse", response);
        return "member/mypage_main";
    }

	/**
	 * 회원정보 수정 페이지
	 * @return
	 */
    @GetMapping("/profile/edit")
    public String editPage(@AuthenticationPrincipal int memberNo ,Model model) {
        MemberResponse response = memberService.retrieve(memberNo);
        List<Map<String, Object>> countries = codeService.getAllCountries();
        List<Map<String, Object>> phoneCodes = codeService.getPhoneCodes();
        model.addAttribute("countries", countries);
        model.addAttribute("phoneCodes",phoneCodes);
        model.addAttribute("memberResponse", response);
        return "member/mypage_edit";
    }

    /**
     * 회원정보 수정
     * @param memberNo
     * @param request
     * @return
     */
    @PutMapping("/profile/edit")
    public ResponseEntity<Object> edit(
            @AuthenticationPrincipal int memberNo,
            @RequestBody MemberRequest request
    ) {
        request.setMemberNo(memberNo);
        memberService.updateMember(request);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }

    /**
     * 비밀번호 일치 확인
     * @param memberNo
     * @param request
     * @return
     */
    @PostMapping("/check/password")
    public ResponseEntity<Object> checkPassword (
            @AuthenticationPrincipal int memberNo,
            @RequestBody MemberRequest request
    ) {
        memberService.checkPassword(memberNo, request);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }

    /**
     * 마이페이지 - 비밀번호 재설정
     * @param memberNo
     * @param request
     * @return
     */
    @PutMapping("/change/password")
    public ResponseEntity<Object> changePassword(
            @AuthenticationPrincipal int memberNo,
            @RequestBody MemberRequest request
    ) {
        memberService.changePassword(memberNo, request);
        String email = memberService.getMemberEmail(memberNo);
        mailUtil.sendPasswordChangeConfirm(email);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }


    /**
     * 회원정보 수정 이메일 중복체크
     * @param memberNo
     * @param email
     * @return
     */
    @GetMapping("/check/email")
    public ResponseEntity<Boolean> checkMemberEmail(
            @AuthenticationPrincipal int memberNo,
            @RequestParam String email
    ) {
        String currentMemberEmail = memberService.getMemberEmail(memberNo);
        if (currentMemberEmail.equals(email)) {
            return ResponseEntity.ok(false);
        }

        boolean isExists = memberService.isMemberEmailExists(email);
        return ResponseEntity.ok(isExists);
    }


    /**
     * 회원 탈퇴 페이지
     * @return
     */
    @GetMapping("/profile/delete")
    public String deleteAccountPage() {
        return "member/mypage_withdrawal";
    }

    /**
     * 회원 탈퇴
     * @param memberNo
     * @return
     */
    @DeleteMapping("/profile/delete")
    public ResponseEntity<Object> deleteAccount(@AuthenticationPrincipal int memberNo) {
        memberService.deleteAccount(memberNo);
        return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
    }



}
