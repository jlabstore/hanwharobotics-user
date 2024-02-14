package com.hanwha.robotics.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private String memberId;

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

}
