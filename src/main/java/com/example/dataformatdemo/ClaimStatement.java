package com.example.dataformatdemo;

import java.io.File;

/**
 * 索赔单
 */
public class ClaimStatement {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();
        // dws层
        String dwsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "索赔单ads.xls";

        // dws 建模 及 spark sql
        if (dwsFilePath != null && !"".equals(dwsFilePath)) {
            dictBaseDataUtil.dwsCreateDDL("pro_claim_form_d", "索赔单", dwsFilePath);
        }
    }
}
