package com.example.dataformatdemo.module.dws;

import com.example.dataformatdemo.util.SqlUtil;

public class BaseDataDwsServiceImpl implements BaseDataDwsService {
    @Override
    public String getDwsTableName(String item) {
        String dws_create_sql = BaseDataDwsConstant.DWS_TABLE_NAME.replaceAll("\\$ITEM\\$", item);
        return dws_create_sql;
    }

    @Override
    public String getDwsCreateTableDDLSql(String item, String itemDesc, String filePath) {
        String dws_table = getDwsTableName(item);
        // 获取字段
        String columns = SqlUtil.getColumns(filePath);
        String create_table_sql = BaseDataDwsSql.dws_create_table_sql.replaceAll("\\$TABLE_NAME\\$", dws_table)
                .replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_DESC\\$", itemDesc);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DWS 建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }
}
