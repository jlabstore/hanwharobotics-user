package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberRequest {

    private int memberNo;
    private String memberId;
    private String password;
    private String newPassword;
    private String companyNm;
    private String region;
    private String city;
    private String postCd;
    private String address;
    private String addressDetail;
    private String store;
    private String memberNm;
    private String position;
    private String email;
    private String contact;


    public Member toEntity() {
        return Member.builder()
                .memberNo(memberNo)
                .memberId(memberId)
                .password(password)
                .companyNm(companyNm)
                .region(region)
                .city(city)
                .postCd(postCd)
                .address(address)
                .addressDetail(addressDetail)
                .store(store)
                .memberNm(memberNm)
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
