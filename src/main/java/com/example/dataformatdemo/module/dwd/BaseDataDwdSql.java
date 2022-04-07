package com.example.dataformatdemo.module.dwd;

public class BaseDataDwdSql {
    /**
     * 明细建模
     */
    public static final String dwd_create_table_sql = "CREATE TABLE $TABLE_NAME$(\n" +
            "$COLUMNS$" +
            "\tsubsidiary_id string COMMENT '子公司id',\n" +
            "\tsubsidiary_code string COMMENT '子公司编码',\n" +
            "\tsubsidiary string COMMENT '子公司',\n" +
            "\tcorporate_id string COMMENT '分公司id',\n" +
            "\tcorporate_code string COMMENT '分公司编码',\n" +
            "\tcorporate string COMMENT '分公司',\n" +
            "\tproject_code string COMMENT '项目编码',\n" +
            "\tpt_tenant_id string COMMENT '平台租户id',\n" +
            "\tpt_org_id string COMMENT '平台机构id',\n" +
            "\torg_id string COMMENT '机构id',\n" +
            "\torg_name string COMMENT '机构名称',\n" +
            "\tsource_org_id string COMMENT '来源机构id',\n" +
            "\tdata_source string COMMENT '数据来源：geps,oa',\n" +
            "\ttenant_id string COMMENT '租户id',\n" +
            "\top_time string COMMENT '操作日期'\n" +
            ")comment '$TABLE_COMMENT$_明细'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    public static final String dwd_spark_sql = "with geps as (\n" +
            "select $ODS_COLUMNS$ from ods.$ODS_TABLE$ WHERE pt='${part_day}' AND  is_enable='Y' AND is_delete='N'),\n" +
            "oa as (SELECT $OA_COLUMNS$ from ods.$OA_TABLE$ where pt='${part_day}'),\n" +
            "org AS(\n" +
            "SELECT\n" +
            "* \n" +
            "FROM dim.dim_org_mapping \n" +
            "WHERE pt='${part_day}'\n" +
            "),\n" +
            "contract AS(\n" +
            "select * from dim.dim_all_contract where pt='${part_day}')\n" +
            "INSERT OVERWRITE TABLE dwd.$DWD_TABLE$ PARTITION(pt='${part_day}')\n" +
            "SELECT\n" +
            "$DWD_COLUMNS$" +
            "b.subsidiary_id,\n" +
            "b.subsidiary_code,\n" +
            "b.subsidiary,\n" +
            "b.corporate_id,\n" +
            "b.corporate_code,\n" +
            "b.corporate,\n" +
            "b.pt_org_code as project_code,\n" +
            "b.pt_tenant_id,\n" +
            "b.pt_org_id,\n" +
            "b.bi_org_id,\n" +
            "b.bi_org_name,\n" +
            "b.ext_org_id AS source_org_id,\n" +
            "a.data_source,\n" +
            "b.bi_tenant_id,\n" +
            "CURRENT_TIMESTAMP as op_time\n" +
            "FROM (SELECT * FROM geps UNION ALL SELECT * FROM oa)AS a\n" +
            "$LEFT_JOIN_TABLES$"+
            "\tLEFT JOIN org as b on a.depart_id= b.ext_org_id;";
}
