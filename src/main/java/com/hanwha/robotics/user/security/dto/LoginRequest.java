package com.hanwha.robotics.user.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String memberId;
    private String password;

    public Authentication toAuthentication() {
        return new LoginAuthentication(memberId, password);
    }

}
