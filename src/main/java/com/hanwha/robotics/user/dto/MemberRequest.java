package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
public class MemberRequest {
    private String memberId;
    private String password;
    private String newPassword;
    private String name;
    private String email;

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }

    public void encryptedPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

//    public void changePassword(String password) {
//        this.newPassword = password;
//    }
}
