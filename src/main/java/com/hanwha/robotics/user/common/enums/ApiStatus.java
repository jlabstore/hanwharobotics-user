package com.hanwha.robotics.user.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiStatus {
    
    OK(200),
    BAD_REQUEST(400),
    SERVER_ERROR(500),
    FORBIDDEN(403);

    private final int value;
 
}
