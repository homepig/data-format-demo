package com.example.dataformatdemo.module.dict.ebs;

public class DictEbsSql {
    public static final String ebs_dict_d_i = "CREATE TABLE $TABLE_NAME$(\n" +
            "\ttenant_id string comment '租户id',\n" +
            "\tdata_type string comment '业务数据类型',\n" +
            "\tdata_list_key string comment '数据键',\n" +
            "\tdata_list_value string comment '数据值',\n" +
            "\tcount string comment '数据数量',\n" +
            "\tlast_update_time string comment '最后更新时间'\n" +
            ")comment 'GEPS_元数据_$TABLE_COMMENT$'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";

    public static final String ebs_dict_delete_d_a = "CREATE TABLE $TABLE_NAME$(\n" +
            "\ttenant_id string comment '租户id',\n" +
            "\tdata_type string comment '业务数据类型',\n" +
            "\tdata_list_key string comment '数据键',\n" +
            "\tdata_list_value string comment '数据值',\n" +
            "\tcount string comment '数据数量'\n" +
            ")comment 'GEPS_元数据_$TABLE_COMMENT$删除'\n" +
            "partitioned by (pt string comment'分区字段')\n" +
            "stored as ORCFILE;";
}
