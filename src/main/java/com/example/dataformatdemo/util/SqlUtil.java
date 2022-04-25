package com.example.dataformatdemo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.dataformatdemo.module.dict.util.ExcelData;

import java.util.HashMap;
import java.util.Map;

public class SqlUtil {
    /**
     * 从excel文件读取列
     *
     * @param filePath
     * @return
     */
    public static String getColumns(String filePath) {
        StringBuffer sb = new StringBuffer();
        Map<String, String> pass = getPassFields();

        EasyExcel.read(filePath, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                String code = excelData.getCode().toLowerCase();
                String desc = excelData.getDesc();
                // 跳过字段处理
                if (pass.containsKey(code)) {
                    continue;
                }
                sb.append("\t").append(code).append(" string COMMENT '");
                if (desc != null && !"".equals(desc)) {
                    desc = desc.replaceAll("（", "").replaceAll("\\(", "")
                            .replaceAll("）", "").replaceAll("\\)", "")
                            .replaceAll(":", "").replaceAll("：", "")
                            .replaceAll(",", "或").replaceAll("，", "或")
                            .replaceAll("%", "百分之");
                    sb.append(desc);
                }
                sb.append("',\r\n");
            }
        })).sheet().doRead();
        return sb.toString();
    }

    private static Map<String, String> getPassFields() {
        Map<String, String> pass = new HashMap<>();
        pass.put("字段编码", "");
        pass.put("pt_tenant_Id", "");
        pass.put("tenant_id", "");
        pass.put("op_time", "");
        pass.put("pt", "");
        pass.put("subsidiary_id", "");
        pass.put("subsidiary_code", "");
        pass.put("subsidiary", "");
        pass.put("corporate_id", "");
        pass.put("corporate_code", "");
        pass.put("corporate", "");
        pass.put("project_code", "");
        pass.put("pt_tenant_id", "");
        pass.put("pt_org_id", "");
        pass.put("org_id", "");
        pass.put("org_name", "");
        pass.put("source_org_id", "");
        pass.put("data_source", "");
        pass.put("tenant_id", "");
        pass.put("op_time", "");
        pass.put("pt", "");
        return pass;
    }
}
