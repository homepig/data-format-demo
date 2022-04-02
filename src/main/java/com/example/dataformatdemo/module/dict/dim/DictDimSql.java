package com.example.dataformatdemo.module.dict.dim;

public class DictDimSql {
    /**
     * 维度建模
     */
    public static final String dim_create_table_sql = "CREATE TABLE $TABLE_NAME$(\n" +
            "$COLUMNS$" +
            "\ttenant_id string COMMENT '租户id',\n" +
            "\top_time string COMMENT '操作日期'\n" +
            ")comment '$TABLE_COMMENT$'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    /**
     * 维度spark
     */
    public static final String dim_spark_sql = "INSERT OVERWRITE TABLE dim.$DIM_TABLE_NAME$ partition(pt='${part_day}')\n" +
            "SELECT \n" +
            "$COLUMNS$" +
            "t.tenant_id as tenant_id," +
            "CURRENT_TIMESTAMP as op_time\n" +
            "from (select * from ods.$ODS_TABLE_NAME$ where pt = '${part_day}' and is_enable = 'Y' and is_delete = 'N') as t;";
}
