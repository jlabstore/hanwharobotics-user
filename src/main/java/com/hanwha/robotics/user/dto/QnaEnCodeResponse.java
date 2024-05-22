package com.hanwha.robotics.user.dto;

import com.hanwha.robotics.user.entity.code.Code;
import com.hanwha.robotics.user.entity.code.ParentCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaEnCodeResponse {
    private List<Code> qnaType1List;
    private List<Code> qnaType2List;
    private List<Code> robotModelList;
    private List<Code> applicationList;

    public QnaEnCodeResponse(Map<ParentCode, List<Code>> collect) {
        this.qnaType1List = collect.get(ParentCode.QNA_TYPE1_EN);
        this.qnaType2List = collect.get(ParentCode.QNA_TYPE2_EN);
        this.robotModelList = collect.get(ParentCode.ROBOT_MODEL);
        this.applicationList = collect.get(ParentCode.ROBOT_APPLICATION);
    }
}
