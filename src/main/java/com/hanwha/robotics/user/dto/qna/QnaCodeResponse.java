package com.hanwha.robotics.user.dto;

import java.util.List;
import java.util.Map;

import com.hanwha.robotics.user.entity.code.Code;
import com.hanwha.robotics.user.entity.code.ParentCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaCodeResponse {
    private List<Code> qnaType1List;
    private List<Code> qnaType2List;
    private List<Code> robotModelList;
    private List<Code> applicationList;

    public QnaCodeResponse(Map<ParentCode, List<Code>> collect) {
        this.qnaType1List = collect.get(ParentCode.QNA_TYPE1);
        this.qnaType2List = collect.get(ParentCode.QNA_TYPE2);
        this.robotModelList = collect.get(ParentCode.ROBOT_MODEL);
        this.applicationList = collect.get(ParentCode.ROBOT_APPLICATION);
    }
}
