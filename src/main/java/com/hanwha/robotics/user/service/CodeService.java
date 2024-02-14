package com.hanwha.robotics.user.service;

import java.util.List;
import java.util.Map;

public interface CodeService {
    List<Map<String, Object>> getAllCountries();

    List<Map<String, Object>> getPhoneCodes();
}
