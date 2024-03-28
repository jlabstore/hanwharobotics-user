package com.hanwha.robotics.user.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

    /**
     * 국자 코드 조회
     * @return
     */
    List<Map<String, Object>> getAllCountries();

    /**
     * 국가 전화번호 조회
     * @return
     */
    List<Map<String, Object>> getPhoneCodes();
}
