package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Member {
    private int memberNo;
    private String memberId;
    private String password;

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

    private String acceptYn;

    private LocalDateTime createDt;
}
