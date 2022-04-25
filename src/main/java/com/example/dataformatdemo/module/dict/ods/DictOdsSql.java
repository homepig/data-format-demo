package com.example.dataformatdemo.module.dict.ods;

public class DictOdsSql {
    /**
     * 增量建模
     */
    public static final String ods_dict_d_i = "CREATE TABLE $TABLE_NAME$ (\n" +
            "$COLUMNS$" +
            "\ttenant_id string  COMMENT '租户id',\n" +
            "\top_time string comment'操作日期'\n" +
            ")comment 'GEPS_元数据_$TABLE_COMMENT$增量'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    /**
     * 删除建模
     */
    public static final String ods_dict_delete_d_a = "CREATE TABLE $TABLE_NAME$ (\n" +
            "\ttenant_id string COMMENT '租户id',\n" +
            "\tdata_type string  COMMENT '业务数据类型',\n" +
            "\tdel_id string  COMMENT '删除单据id',\n" +
            "\tdel_time string  COMMENT '删除时间',\n" +
            "\top_time string  COMMENT '操作时间'\n" +
            ")comment 'GEPS_元数据_$TABLE_COMMENT$删除'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    /**
     * 全量建模
     */
    public static final String ods_dict_d_a = "CREATE TABLE $TABLE_NAME$ (\n" +
            "$COLUMNS$" +
            "\ttenant_id string  COMMENT '租户id',\n" +
            "\top_time string comment'操作日期',\n" +
            "\tend_date string comment'截止日期',\n" +
            "\tis_enable string comment'是否有效',\n" +
            "\tis_delete string comment'是否删除'\n" +
            ")comment 'GEPS_元数据_$TABLE_COMMENT$全量'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    /**
     * 增量spark sql
     */
    public static final String ods_dict_d_i_spark = "INSERT OVERWRITE TABLE ods.$ODS_TABLE_NAME$ partition(pt='${part_day}')\n" +
            "SELECT \n" +
            "$SELECT_COLUMNS$" +
            "t.tenant_id,\n" +
            "CURRENT_TIMESTAMP as op_time \n" +
            "FROM ebs.$EBS_TABLE_NAME$ as t lateral view json_tuple(t.data_list_value,\n" +
            "$JSON_COLUMNS$)b as \n" +
            "$JSON_AS_COLUMNS$\n" +
            "WHERE t.pt='${part_day}';";

    public static final String ods_dict_delete_d_a_spark = "INSERT\n" +
            "\tOVERWRITE TABLE ods.$ODS_TABLE_NAME$ partition(pt = '${part_day}')\n" +
            "select\n" +
            "\tt.tenant_id,\n" +
            "\tt.data_type,\n" +
            "\tb.id,\n" +
            "\tpt,\n" +
            "\tcurrent_date \n" +
            "FROM ebs.$EBS_TABLE_NAME$ as t lateral view json_tuple(t.data_list_value,\n" +
            "'id') b as id\n" +
            "WHERE t.pt ='${part_day}';";

    public static final String ods_dict_d_a_spark = "WITH yesterday_a AS (SELECT $SELECT_COLUMNS_V$,end_date,is_delete FROM ods.$ODS_DA_TABLE$ WHERE pt='${part_yesterday}' AND id IS NOT NULL  AND id !=''),\n" +
            "day_i AS(SELECT $SELECT_COLUMNS_V$,'' AS end_date,'' AS is_delete\n" +
            " FROM(SELECT a.*,ROW_NUMBER() OVER(PARTITION BY id ,last_update_time ORDER BY last_update_time) as rn FROM ods.$ODS_DI_TABLE$ AS a WHERE a.pt='${part_day}' AND a.id IS NOT NULL AND a.id !='' AND NOT EXISTS(SELECT 1 FROM yesterday_a AS b WHERE a.id=b.id AND a.last_update_time=b.last_update_time)) tt WHERE tt.rn=1),\n" +
            "ods_del AS(SELECT * FROM ods.$ODS_DD_TABLE$ WHERE pt='${part_day}'  AND del_id IS NOT NULL  AND del_id !='')\n" +
            "INSERT OVERWRITE TABLE ods.$ODS_DA_TABLE$ PARTITION(pt='${part_day}')\n" +
            "SELECT $SELECT_COLUMNS$,CURRENT_TIMESTAMP as op_time,\n" +
//            "IF(b.del_id is NOT null AND  b.del_id !='','${part_day}','9999-12-31') AS end_date," +
            "IF(a.end_date is not null AND a.end_date != '' AND a.end_date != '9999-12-31',a.end_date,IF(b.del_id is NOT null AND b.del_id !='','${part_day}','9999-12-31')) AS end_date,\n"+
            "IF(ROW_NUMBER() OVER(PARTITION BY a.id ORDER BY a.last_update_time DESC)=1,'Y','N') as is_enable," +
//            "IF(b.del_id is NOT null AND  b.del_id !='','Y','N') AS is_delete \n" +
            "IF(a.is_delete='Y',a.is_delete,IF(b.del_id is NOT null AND b.del_id !='','Y','N')) AS is_delete \n"+
            "FROM (SELECT * FROM yesterday_a UNION ALL SELECT * FROM day_i) as a LEFT JOIN ods_del AS b ON  a.id=b.del_id;";
}
