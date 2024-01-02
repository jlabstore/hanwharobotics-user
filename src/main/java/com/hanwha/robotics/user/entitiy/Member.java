package com.hanwha.robotics.user.entitiy;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Member {
    private int memberNo;
    private String memberId;
    private String password;
    private String name;
    private String email;
    private Date createDt;
    private String acceptYn;
    private String useYn;
}
