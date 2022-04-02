package com.example.dataformatdemo.module.dict.dim;

import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsService;

public interface DictBaseDataModuleDimService {

    void setDictBaseDataModuleOdsService(DictBaseDataModuleOdsService dictBaseDataModuleOdsService);

    /**
     * 获取Dim表名
     *
     * @param item
     * @return
     */
    String getDimTableName(String item);

    /**
     * 获取维度建模语句
     *
     * @param item
     * @param tableComment
     * @param dimFilePath  文件全路径
     * @return
     */
    String getDimCreateTableSql(String item, String tableComment, String dimFilePath);

    /**
     * 获取维度spark 语句
     *
     * @param odsItem
     * @param dimItem
     * @param odsJson
     * @return
     */
    String getDimSparkSql(String odsItem, String dimItem, String odsJson);
}
