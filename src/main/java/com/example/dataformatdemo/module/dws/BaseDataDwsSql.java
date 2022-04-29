package com.example.dataformatdemo.module.dws;

public class BaseDataDwsSql {
    public static final String dws_create_table_sql = "CREATE TABLE $TABLE_NAME$(\n" +
            "$COLUMNS$" +
            "\tyear string COMMENT '年',\n" +
            "\tmonth string COMMENT '月',\n" +
            "\ttype string COMMENT '数据行类型1是明细2是合计',\n" +
            "\tproject_code string COMMENT '项目编码',\n" +
            "\tsubsidiary_id string COMMENT '子公司id',\n" +
            "\tsubsidiary_code string COMMENT '子公司编码',\n" +
            "\tsubsidiary string COMMENT '子公司',\n" +
            "\tcorporate_id string COMMENT '分公司id',\n" +
            "\tcorporate_code string COMMENT '分公司编码',\n" +
            "\tcorporate string COMMENT '分公司',\n" +
            "\tpt_tenant_id string COMMENT '平台租户id',\n" +
            "\tpt_org_id string COMMENT '平台机构id',\n" +
            "\torg_id string COMMENT '机构id',\n" +
            "\torg_name string COMMENT '机构名称',\n" +
            "\tsource_org_id string COMMENT '来源机构id',\n" +
            "\tdata_source string COMMENT '数据来源geps或oa',\n" +
            "\ttenant_id string COMMENT '租户id',\n" +
            "\top_time string COMMENT '操作日期'\n" +
            ")comment '$TABLE_DESC$'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";
}
