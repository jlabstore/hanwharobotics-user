package com.hanwha.robotics.user.entity.code;

import com.hanwha.robotics.user.entity.code.ParentCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Code {
    private String code;
    private ParentCode parentCode;
    private String codeName;
    private String description;
}