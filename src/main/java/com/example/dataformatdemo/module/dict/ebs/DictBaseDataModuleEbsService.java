package com.example.dataformatdemo.module.dict.ebs;

/*
@author fc
 */
public interface DictBaseDataModuleEbsService {
    /**
     * 获取ebs增量表名
     *
     * @param item
     * @return
     */
    String getEbsDITableName(String item);

    /**
     * 获取ebs删除表名
     *
     * @param item
     * @return
     */
    String getEbsDATableName(String item);

    /**
     * 获取ebs建表语句
     *
     * @param item
     * @param tableComment
     * @param createDeleteTable 是否穿件删除表
     * @return
     */
    String[] getEbsCreateTableSql(String item, String tableComment, boolean createDeleteTable);
}
