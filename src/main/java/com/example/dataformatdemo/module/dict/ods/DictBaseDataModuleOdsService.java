package com.example.dataformatdemo.module.dict.ods;

import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsService;

public interface DictBaseDataModuleOdsService {
    void setDictBaseDataModuleEbsService(DictBaseDataModuleEbsService dictBaseDataModuleEbsService);

    /**
     * 获取ods增量表名
     *
     * @param item
     * @return
     */
    String getOdsDITableName(String item);

    /**
     * 获取ods删除表名
     *
     * @param item
     * @return
     */
    String getOdsDDTableName(String item);

    /**
     * 获取ods全量表名
     *
     * @param item
     * @return
     */
    String getOdsDATableName(String item);

    /**
     * 获取字段名
     *
     * @param jsonString
     * @return
     */
    String getFieldsSqlFromJsonString(String jsonString);

    /**
     * 获取增量建表语句
     *
     * @param item
     * @param tableComment
     * @param jsonString
     * @return
     */
    String getObsDictDISql(String item, String tableComment, String jsonString);

    /**
     * 获取删除表穿件语句
     *
     * @param item
     * @param tableComment
     * @return
     */
    String getObsDictDeleteDASql(String item, String tableComment);

    /**
     * 获取全量建表语句
     *
     * @param item
     * @param tableComment
     * @param jsonString
     * @return
     */
    String getObsDictDASql(String item, String tableComment, String jsonString);

    /**
     * 获取增量spark语句
     *
     * @param item
     * @param jsonString
     * @return
     */
    String getObsDictDISparkSql(String item, String jsonString);

    /**
     * 获取删除spark语句
     *
     * @param item
     * @return
     */
    String getObsDictDdSparkSql(String item);

    /**
     * 获取全量spark语句
     *
     * @param item
     * @param jsonString
     * @return
     */
    String getObsDictDASparkSql(String item, String jsonString);
}
