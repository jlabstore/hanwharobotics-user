package com.hanwha.robotics.user.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeletedAccount {
    private int memberNo;
    private String memberId;
}
