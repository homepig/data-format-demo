package com.example.dataformatdemo.module.dict.util;

import lombok.Data;

@Data
public class ExcelData {
    private String index;
    private String desc;
    private String code;
    private String fieldType;
    private String primaryKey;
    private String empty;
    private String rule;
    /**
     * ods 来源
     */
    private String odsSource;
    /**
     * oa 来源
     */
    private String oaSource;
}
