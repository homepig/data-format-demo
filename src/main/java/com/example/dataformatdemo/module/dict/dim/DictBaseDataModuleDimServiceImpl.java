package com.example.dataformatdemo.module.dict.dim;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSONObject;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsService;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsServiceImpl;
import com.example.dataformatdemo.module.dict.util.ExcelData;

import java.util.HashMap;
import java.util.Map;

public class DictBaseDataModuleDimServiceImpl implements DictBaseDataModuleDimService {

    private DictBaseDataModuleOdsService dictBaseDataModuleOdsService;

    @Override
    public void setDictBaseDataModuleOdsService(DictBaseDataModuleOdsService dictBaseDataModuleOdsService) {
        this.dictBaseDataModuleOdsService = dictBaseDataModuleOdsService;
    }

    @Override
    public String getDimTableName(String item) {
        String dim_table_name = DictDimConstant.dim_table.replaceAll("\\$ITEM\\$", item);
        System.out.println(dim_table_name);
        return dim_table_name;
    }

    @Override
    public String getDimCreateTableSql(String item, String tableComment, String dimFilePath) {
        // 获取表名
        String table_name = getDimTableName(item);
        // 获取字段
        String columns = this.getColumns(dimFilePath);
        String create_table_sql = DictDimSql.dim_create_table_sql.replaceAll("\\$TABLE_NAME\\$", table_name)
                .replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$",tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DIM 建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getDimSparkSql(String odsItem, String dimItem, String odsJson) {
        // 获取ods全量表名
        String ods_d_a_table = this.dictBaseDataModuleOdsService.getOdsDATableName(odsItem);
        // 获取维度表名
        String dim_table = getDimTableName(dimItem);
        // 生成spark sql
        String columns = this.getSparkSelectColumns(odsJson);
        String spark_sql = DictDimSql.dim_spark_sql.replaceAll("\\$DIM_TABLE_NAME\\$", dim_table)
                .replaceAll("\\$ODS_TABLE_NAME\\$", ods_d_a_table).replaceAll("\\$COLUMNS\\$", columns);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>DIM SparkSQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(spark_sql);
        System.out.println("\r\n");
        return spark_sql;
    }

    private String getColumns(String dimFilePath) {
        StringBuffer sb = new StringBuffer();
        Map<String, String> pass = new HashMap<>();
        pass.put("字段编码", "");
        pass.put("tenant_Id", "");
        pass.put("op_time", "");
        pass.put("pt", "");
        EasyExcel.read(dimFilePath, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                String code = excelData.getCode();
                String desc = excelData.getDesc();
                // 跳过字段处理
                if (pass.containsKey(code)) {
                    continue;
                }
                sb.append("\t").append(code).append(" string COMMENT '");
                if (desc != null && !"".equals(desc)) {
                    sb.append(desc);
                }
                sb.append("',\r\n");
            }
        })).sheet().doRead();
        return sb.toString();
    }

    private String getSparkSelectColumns(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        StringBuffer select_columns = new StringBuffer();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("LastUpdateTime") || entry.getKey().equals("ID")) {
                continue;
            }
            String field = entry.getKey().replaceAll("\\.", "_").toLowerCase();
            select_columns.append("t.").append(field).append(" as ").append(field).append(",").append("\r\n");
        }
        return select_columns.toString();
    }
}
