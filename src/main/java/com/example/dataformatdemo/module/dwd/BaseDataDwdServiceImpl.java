package com.example.dataformatdemo.module.dwd;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.dataformatdemo.module.dict.util.ExcelData;
import com.example.dataformatdemo.util.SqlUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class BaseDataDwdServiceImpl implements BaseDataDwdService {
    @Override
    public String getDwdTableName(String item) {
        String dwd_table_name = BaseDataDwdConstant.DWD_TABLE_NAME.replaceAll("\\$ITEM\\$", item);
        return dwd_table_name;
    }

    @Override
    public String getDwdCreateTableDDL(String item, String desc, String filePath) {
        // 获取表名
        String table_name = getDwdTableName(item);
        // 获取字段
        String columns = SqlUtil.getColumns(filePath);
        String create_table_sql = BaseDataDwdSql.dwd_create_table_sql.replaceAll("\\$TABLE_NAME\\$", table_name)
                .replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", desc);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DWD 建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getDwdSparkSql(String item, String odsTable, String oaTable, String filePath) {
        // 获取dwd表名
        String dwd_table = getDwdTableName(item);
        DwdSparkSqlPart dwdSparkSqlPart = this.getDwdSparkSqlPart(filePath);
        String dwd_spark_sql = BaseDataDwdSql.dwd_spark_sql.replaceAll("\\$ODS_COLUMNS\\$", dwdSparkSqlPart.getOdsColumns())
                .replaceAll("\\$ODS_TABLE\\$", odsTable).replaceAll("\\$OA_COLUMNS\\$", dwdSparkSqlPart.getOaColumns())
                .replaceAll("\\$OA_TABLE\\$", oaTable).replaceAll("\\$DWD_TABLE\\$", dwd_table)
                .replaceAll("\\$DWD_COLUMNS\\$", dwdSparkSqlPart.getDwdColumns()).replaceAll("\\$LEFT_JOIN_TABLES\\$", dwdSparkSqlPart.getJoinSql());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DWD SparkSQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(dwd_spark_sql);
        System.out.println("\r\n");
        return dwd_spark_sql;
    }

    private DwdSparkSqlPart getDwdSparkSqlPart(String filePath) {
        Map<String, String> pass = this.getPassFields();
        StringBuffer odsColumns = new StringBuffer();
        StringBuffer oaColumns = new StringBuffer();
        StringBuffer dwdColumns = new StringBuffer();
        StringBuffer joinSql = new StringBuffer();

        EasyExcel.read(filePath, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                // dwd 字段名
                String code = excelData.getCode().toLowerCase();

                // 跳过字段处理
                if (pass.containsKey(code)) {
                    continue;
                }


                // dwd 字段注释
                String desc = excelData.getDesc();
                // ods 对应字段
                String ods_field = excelData.getOdsSource();

                // oa 对应字段
                String oa_field = excelData.getOaSource();

                if (code.equals("data_source")) {
                    odsColumns.append("\t").append("'geps'").append(" AS ").append(code).append("\r\n");
                    oaColumns.append("\t").append("'oa'").append(" AS ").append(code).append("\r\n");
                    continue;
                }

                if (ods_field != null && !"".equals(ods_field)) {
//                    if (ods_field.equals("dept_deptid")) {
//                        odsColumns.append("\t").append(ods_field.toLowerCase()).append(" AS ")
//                                .append("org_id").append(",").append("--").append(desc).append("\r\n");
//                    }else {
//                        odsColumns.append("\t").append(ods_field.toLowerCase()).append(" AS ")
//                                .append(code).append(",").append("--").append(desc).append("\r\n");
//                    }
                    odsColumns.append("\t").append(ods_field.toLowerCase()).append(" AS ")
                            .append(code).append(",").append("--").append(desc).append("\r\n");
                } else {
                    odsColumns.append("\t").append("''").append(" AS ").append(code).append(",").append("--").append(desc).append("\r\n");
                }


                if (oa_field != null && !"".equals(oa_field)) {
//                    if (oa_field.equals("xmcode")) {
//                        oaColumns.append("\t").append(oa_field.toLowerCase()).append(" AS ").append("org_id").append(",").append("--").append(desc).append("\r\n");
//                    }else {
//                        oaColumns.append("\t").append(oa_field.toLowerCase()).append(" AS ").append(code).append(",").append("--").append(desc).append("\r\n");
//                    }
                    oaColumns.append("\t").append(oa_field.toLowerCase()).append(" AS ").append(code).append(",").append("--").append(desc).append("\r\n");
                } else {
                    oaColumns.append("\t").append("''").append(" AS ").append(code).append(",").append("--").append(desc).append("\r\n");
                }

                // 数据清洗
                if (code.equals("contract_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN contract as c on a.contract_id = c.id").append("\r\n");
                } else if (code.equals("contract_name")) {
                    dwdColumns.append("\t").append("c.name").append(",").append("\r\n");
                } else if (code.equals("project_belong_company_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN org as company on a.project_belong_company_id = company.ext_org_id").append("\r\n");
                } else if (code.equals("project_belong_company_name")) {
                    dwdColumns.append("\t").append("company.ext_org_name").append(",").append("\r\n");
                } else if (code.equals("depart_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN org as depart on a.depart_id = depart.ext_org_id").append("\r\n");
                } else if (code.equals("depart_name")) {
                    dwdColumns.append("\t").append("depart.ext_org_name").append(",").append("\r\n");
                } else {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                }
            }
        })).sheet().doRead();
        DwdSparkSqlPart dwdSparkSqlPart = new DwdSparkSqlPart();
        dwdSparkSqlPart.setOdsColumns(odsColumns.toString());
        dwdSparkSqlPart.setOaColumns(oaColumns.toString());
        dwdSparkSqlPart.setDwdColumns(dwdColumns.toString());
        dwdSparkSqlPart.setJoinSql(joinSql.toString());
        return dwdSparkSqlPart;
    }

    private Map<String, String> getPassFields() {
        Map<String, String> pass = new HashMap<>();
        pass.put("字段编码", "字段编码");
        pass.put("subsidiary_id", "subsidiary_id");
        pass.put("subsidiary_code", "subsidiary_code");
        pass.put("subsidiary", "subsidiary");
        pass.put("corporate_id", "corporate_id");
        pass.put("corporate_code", "corporate_code");
        pass.put("corporate", "corporate");
        pass.put("project_code", "project_code");
        pass.put("pt_tenant_id", "pt_tenant_id");
        pass.put("pt_org_id", "pt_org_id");
        pass.put("org_id", "org_id");
        pass.put("org_name", "org_name");
        pass.put("source_org_id", "source_org_id");
        pass.put("tenant_id", "tenant_id");
        pass.put("op_time", "op_time");
        pass.put("pt", "pt");
        return pass;
    }

    @Data
    private class DwdSparkSqlPart {
        private String odsColumns;

        private String oaColumns;

        private String dwdColumns;

        private String joinSql;
    }
}
