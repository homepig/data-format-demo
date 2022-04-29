package com.example.dataformatdemo.module.dws;

public interface BaseDataDwsService {
    String getDwsTableName(String item);

    String getDwsCreateTableDDLSql(String item, String itemDesc, String filePath);

//    String getDwsSparkSql(String item,String bizDateField,String )
}
