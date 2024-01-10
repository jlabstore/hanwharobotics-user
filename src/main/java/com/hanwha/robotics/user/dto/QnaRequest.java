package com.hanwha.robotics.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QnaRequest {
    public String title;
    public String content;
}
