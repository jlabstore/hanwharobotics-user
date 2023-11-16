package com.hanwha.robotics.user.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NewsroomType {
    
    IR("NEWSROOM_IR"),
    Notice("NEWSROOM_NOTICE"),
    Press("NEWSROOM_PRESS");

    private String code;
 
}
