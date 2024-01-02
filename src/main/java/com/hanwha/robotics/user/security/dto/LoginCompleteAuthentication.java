package com.hanwha.robotics.user.security.dto;

import com.hanwha.robotics.user.entity.Member;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

@Getter
public class LoginCompleteAuthentication extends UsernamePasswordAuthenticationToken {


    public LoginCompleteAuthentication(Member member) {
        super(member.getMemberId(), null, Collections.emptyList());
    }
}
