package com.example.dataformatdemo.module.dwd;

public interface BaseDataDwdService {
    /**
     * 获取dwd层表名
     *
     * @param item
     * @return
     */
    String getDwdTableName(String item);

    /**
     * 获取 dwd层 建模语句
     *
     * @param item
     * @param desc
     * @param filePath 表结构excel
     * @return
     */
    String getDwdCreateTableDDL(String item, String desc, String filePath);

    /**
     * 获取dwd spark sql
     *
     * @param item
     * @param odsTable
     * @param oaTable
     * @param filePath
     * @return
     */
    String getDwdSparkSql(String item, String odsTable, String oaTable, String filePath);
}
