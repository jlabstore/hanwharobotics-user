package com.hanwha.robotics.user.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NewsroomType {
    
    IR("NEWSROOM_IR"),
    NOTICE("NEWSROOM_NOTICE"),
    PRESS("NEWSROOM_PRESS");

    private String code;
 
}
