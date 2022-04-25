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
                .replaceAll("\\$ODS_TABLE\\$", odsTable)
                .replaceAll("\\$DWD_TABLE\\$", dwd_table)
                .replaceAll("\\$DWD_COLUMNS\\$", dwdSparkSqlPart.getDwdColumns()).replaceAll("\\$LEFT_JOIN_TABLES\\$", dwdSparkSqlPart.getJoinSql());
        if (oaTable != null && !"".equals(oaTable)) {
            dwd_spark_sql = dwd_spark_sql.replaceAll("\\$OA_COLUMNS\\$", dwdSparkSqlPart.getOaColumns())
                    .replaceAll("\\$OA_TABLE\\$", oaTable);
        } else {
            dwd_spark_sql = dwd_spark_sql.replaceAll("\\$OA_COLUMNS\\$", "")
                    .replaceAll("\\$OA_TABLE\\$", "");
        }
        if (dwdSparkSqlPart.getWithSql() != null && !"".equals(dwdSparkSqlPart.getWithSql())) {
            dwd_spark_sql = dwd_spark_sql.replaceAll("\\$WITH_SQL\\$", dwdSparkSqlPart.getWithSql());
        } else {
            dwd_spark_sql = dwd_spark_sql.replaceAll("\\$WITH_SQL\\$", "");
        }
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
        StringBuffer withSql = new StringBuffer();

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
                } else if (code.equals("project_belong_company_id") || code.equals("purchase_project_id")) {
                    // 项目对应的组织
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
//                    joinSql.append("\t").append("LEFT JOIN org as company on a.project_belong_company_id = company.ext_org_id").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN org as company on a.").append(code).append(" = company.ext_org_id").append("\r\n");
                } else if (code.equals("project_belong_company_name") || code.equals("purchase_project_name")) {
                    // 项目对应的组织
                    dwdColumns.append("\t").append("company.ext_org_name").append(",").append("\r\n");
                } else if (code.equals("depart_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN org as depart on a.depart_id = depart.ext_org_id").append("\r\n");
                } else if (code.equals("depart_name")) {
                    dwdColumns.append("\t").append("depart.ext_org_name").append(",").append("\r\n");
                } else if (code.equals("calculate_obj_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN account on a.calculate_obj_id = account.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.hsdx_sql);
                } else if (code.equals("calculate_obj_name")) {
                    dwdColumns.append("\t").append("account.name").append(",").append("\r\n");
                } else if (code.equals("contact_unit_id") || code.equals("cooperation_unit_dict_id")
                        || code.equals("party_a_id") || code.equals("win_bid_supplier_id") || code.equals("party_b_id")) {
                    // 合作单位
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN unit on a.").append(code).append(" = unit.bill_id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.hzdw_sql);
                } else if (code.equals("contact_unit_name") || code.equals("party_a_name") || code.equals("win_bid_supplier") ||
                        code.equals("party_b_name")) {
                    //TODO 有一个名字待补充
                    dwdColumns.append("\t").append("unit.name").append(",").append("\r\n");
                } else if (code.equals("project_basic_info_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN project on a.project_basic_info_id = project.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.xmjbxx_sql);
                } else if (code.equals("project_basic_info_name")) {
                    dwdColumns.append("\t").append("project.name").append(",").append("\r\n");
                } else if (code.equals("build_contract_reg_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN contractbuild on a.build_contract_reg_id = contractbuild.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.sghtdj_sql);
                } else if (code.equals("build_contract_reg_name")) {
                    dwdColumns.append("\t").append("contractbuild.name").append(",").append("\r\n");
                } else if (code.equals("requisition_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN org as requisition on a.requisition_id = requisition.ext_org_id").append("\r\n");
                } else if (code.equals("requisition_name")) {
                    dwdColumns.append("\t").append("requisition.ext_org_name").append(",").append("\r\n");
                } else if (code.equals("payment_type_set_dict_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN paytype on a.payment_type_set_dict_id = paytype.bill_id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.zjzf_sql);
                } else if (code.equals("payment_type_set_dict_name")) {
                    dwdColumns.append("\t").append("paytype.payment_type").append(",").append("\r\n");
                } else if (code.equals("project_borrowing_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN borrowmoney on a.project_borrowing_id = borrowmoney.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.xmjk_sql);
                } else if (code.equals("project_borrowing_name")) {
                    dwdColumns.append("\t").append("borrowmoney.name").append(",").append("\r\n");
                } else if (code.equals("output_category_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN xxfpfl on a.output_category_id = xxfpfl.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.xxfpfl_sql);
                } else if (code.equals("output_category_name")) {
                    dwdColumns.append("\t").append("xxfpfl.name").append(",").append("\r\n");
                } else if (code.equals("match_contract_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN htjbxxb on a.match_contract_id = htjbxxb.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.htjbxxb_sql);
                } else if (code.equals("match_contract_name")) {
                    dwdColumns.append("\t").append("htjbxxb.name").append(",").append("\r\n");
                } else if (code.equals("tax_reduction_dict_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN jsxzdict on a.tax_reduction_dict_id = jsxzdict.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.jsxzdict_sql);
                } else if (code.equals("tax_reduction_dict_name")) {
                    dwdColumns.append("\t").append("jsxzdict.name").append(",").append("\r\n");
                } else if (code.equals("taxable_project_code_dict_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN ysxmdict on a.taxable_project_code_dict_id = ysxmdict.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.ysxmdict_sql);
                } else if (code.equals("taxable_project_code_dict_name")) {
                    dwdColumns.append("\t").append("ysxmdict.name").append(",").append("\r\n");
                } else if (code.equals("region_dict_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN dyzd on a.region_dict_id = dyzd.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.dyzd_sql);
                } else if (code.equals("region_dict_name")) {
                    dwdColumns.append("\t").append("dyzd.name").append(",").append("\r\n");
                } else if (code.equals("material_category_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN cllb on a.material_category_id = cllb.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.cllb_sql);
                } else if (code.equals("material_category")) {
                    dwdColumns.append("\t").append("cllb.name").append(",").append("\r\n");
                } else if (code.equals("party_b_taxpayer_category_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN nslb on a.party_b_taxpayer_category_id = nslb.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.nslb_sql);
                } else if (code.equals("party_b_taxpayer_category")) {
                    dwdColumns.append("\t").append("nslb.name").append(",").append("\r\n");
                } else if (code.equals("contract_category_id")) {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                    joinSql.append("\t").append("LEFT JOIN htfl on a.contract_category_id = htfl.id").append("\r\n");
                    withSql.append(BaseDataDwdConstant.htfl_sql);
                } else if (code.equals("contract_category_name")) {
                    dwdColumns.append("\t").append("htfl.name").append(",").append("\r\n");
                } else if (code.equals("")){

                }else {
                    dwdColumns.append("\t").append("a.").append(code).append(",").append("\r\n");
                }
            }
        })).sheet().doRead();
        DwdSparkSqlPart dwdSparkSqlPart = new DwdSparkSqlPart();
        dwdSparkSqlPart.setOdsColumns(odsColumns.toString());
        dwdSparkSqlPart.setOaColumns(oaColumns.toString());
        dwdSparkSqlPart.setDwdColumns(dwdColumns.toString());
        dwdSparkSqlPart.setJoinSql(joinSql.toString());
        dwdSparkSqlPart.setWithSql(withSql.toString());
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

        private String withSql;
    }
}
