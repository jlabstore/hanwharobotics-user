package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberRequest {

    private String memberId;
    private String password;
    private String newPassword;

    private String companyName;
    private String region;
    private String city;
    private String postCode;
    private String address;
    private String addressDetail;

    private String store;
    private String name;
    private String position;

    private String email;
    private String contact;


    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .companyName(companyName)
                .region(region)
                .city(city)
                .postCode(postCode)
                .address(address)
                .addressDetail(addressDetail)
                .store(store)
                .name(name)
                .position(position)
                .email(email)
                .contact(contact)
                .build();
    }

    public void encryptedPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

//    public void changePassword(String password) {
//        this.newPassword = password;
//    }
}
