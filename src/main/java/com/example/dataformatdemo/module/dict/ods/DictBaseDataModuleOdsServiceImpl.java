package com.example.dataformatdemo.module.dict.ods;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSONObject;
import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsService;
import com.example.dataformatdemo.module.dict.util.ExcelData;
import lombok.Data;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DictBaseDataModuleOdsServiceImpl implements DictBaseDataModuleOdsService {

    private DictBaseDataModuleEbsService dictBaseDataModuleEbsService;

    public void setDictBaseDataModuleEbsService(DictBaseDataModuleEbsService dictBaseDataModuleEbsService) {
        this.dictBaseDataModuleEbsService = dictBaseDataModuleEbsService;
    }

    @Override
    public String getOdsDITableName(String item) {
        String ods_d_i_table_name = DictOdsConstant.ODS_DICT_D_I.replaceAll("\\$ITEM\\$", item);
        return ods_d_i_table_name;
    }

    @Override
    public String getOdsDDTableName(String item) {
        String ods_delete_d_i_table_name = DictOdsConstant.ODS_DICT_DELETE_D_A.replaceAll("\\$ITEM\\$", item);
        return ods_delete_d_i_table_name;
    }

    @Override
    public String getOdsDATableName(String item) {
        String ods_d_a_table_name = DictOdsConstant.ODS_DICT_D_A.replaceAll("\\$ITEM\\$", item);
        return ods_d_a_table_name;
    }

    @Override
    public String getFieldsSqlFromJsonString(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        StringBuffer sb = new StringBuffer();
        Map<String, String> comments = getFieldComments();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("LastUpdateTime") || entry.getKey().equals("ID")) {
                continue;
            }
            String field = entry.getKey().replaceAll("\\.", "_").toLowerCase();
            String comment = comments.get(field);
            sb.append("\t").append(field).append(" ").append("string COMMENT '").append(comment).append("',").append("\r\n");
        }
        return sb.toString();
    }

    @Override
    public String getFieldsSqlFromJsonString(String jsonString, String odsFilePath) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        StringBuffer sb = new StringBuffer();
        Map<String, String> comments = getFieldComments();
        // 从文件提取注释
        Map<String, String> getExcelComments = getColumnComments(odsFilePath);
        comments.putAll(getExcelComments);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("LastUpdateTime") || entry.getKey().equals("ID")) {
                continue;
            }
            String field = entry.getKey().replaceAll("\\.", "_").toLowerCase();
            String comment = comments.get(field);
            sb.append("\t").append(field).append(" ").append("string COMMENT '").append(comment).append("',").append("\r\n");
        }
        return sb.toString();
    }

    private String getComment(String field, Map<String, String> comments) {
        String comment = comments.get(field);
        if (comment == null) {
            return "";
        }
        return comment;
    }

    private Map<String, String> getFieldComments() {
        Map<String, String> comments = new HashMap<>();
        comments.put("ywlx", "业务类型");
        comments.put("ytlx", "用途类型");
        comments.put("code", "编码");
        comments.put("jianpin", "简拼");
        comments.put("name", "名称");
        comments.put("remark", "备注");
        comments.put("master_bill_id", "单据ID");
        comments.put("lastupdateuser", "最后修改人");
        comments.put("last_update_time", "最后修改时间");
        comments.put("org_id", "机构ID");
        comments.put("dept_deptid", "所属部门ID");
        comments.put("pinyin", "拼音");
        comments.put("creator_id", "创建人ID");
        comments.put("creator_name", "创建人");
        comments.put("id", "ID");
        comments.put("data_status", "数据状态");
        comments.put("org_name", "机构名称");
        comments.put("fq", "废弃");
        comments.put("category_id", "类别ID");
        comments.put("isleaf", "是否叶子结点");
        comments.put("orderno", "序列号");
        comments.put("fullcode", "全编码");
        comments.put("xtbs", "系统标识");
        comments.put("htlx", "合同类型");
        comments.put("issystem", "是否系统自带");
        comments.put("level", "级别");
        comments.put("pid", "父id");
        comments.put("htflsx", "合同分类属性");
        comments.put("htflsx_id", "合同分类属性ID");
        return comments;
    }

    private Map<String, String> getColumnComments(String filePath) {
        Map<String, String> comments = new LinkedHashMap<>();
        EasyExcel.read(filePath, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                String code = excelData.getCode();
                String desc = excelData.getDesc();
                if (code != null && desc != null && !"".equals(desc)) {
                    comments.put(code, desc);
                }
            }
        })).doReadAll();
        return comments;
    }

    @Override
    public String getObsDictDISql(String item, String tableComment, String jsonString) {
        String ods_dict_d_i_table = this.getOdsDITableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString);
        String create_table_sql = DictOdsSql.ods_dict_d_i.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods增量建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDISql(String item, String tableComment, String jsonString, String odsFilePath) {
        String ods_dict_d_i_table = this.getOdsDITableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString, odsFilePath);
        String create_table_sql = DictOdsSql.ods_dict_d_i.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods增量建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDeleteDASql(String item, String tableComment) {
        String ods_dict_delete_d_a = this.getOdsDDTableName(item);
        String create_table_sql = DictOdsSql.ods_dict_delete_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_delete_d_a).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods删除建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDASql(String item, String tableComment, String jsonString) {
        String ods_dict_d_i_table = this.getOdsDATableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString);
        String create_table_sql = DictOdsSql.ods_dict_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods全量建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDASql(String item, String tableComment, String jsonString, String odsFilePath) {
        String ods_dict_d_i_table = this.getOdsDATableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString, odsFilePath);
        String create_table_sql = DictOdsSql.ods_dict_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods全量建模<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDISparkSql(String item, String jsonString) {
        // 获取ebs表名
        String ebs_table = dictBaseDataModuleEbsService.getEbsDITableName(item);

        // 获取 obs 表名
        String obs_table = this.getOdsDITableName(item);

        DiSparkSqlPart diSparkSqlPart = this.getObsDiSparkSqlPart(jsonString);

        String sparkSql = DictOdsSql.ods_dict_d_i_spark.replaceAll("\\$ODS_TABLE_NAME\\$", obs_table)
                .replaceAll("\\$EBS_TABLE_NAME\\$", ebs_table).replaceAll("\\$SELECT_COLUMNS\\$", diSparkSqlPart.select_columns)
                .replaceAll("\\$JSON_COLUMNS\\$", diSparkSqlPart.json_columns).replaceAll("\\$JSON_AS_COLUMNS\\$", diSparkSqlPart.json_as_columns);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>增量Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(sparkSql);
        System.out.println("\r\n");
        return sparkSql;
    }

    @Override
    public String getObsDictDdSparkSql(String item) {
        // 获取ebs表名
        String ebs_table = dictBaseDataModuleEbsService.getEbsDATableName(item);

        // 获取ods表名
        String ods_table = this.getOdsDDTableName(item);
        String sparkSql = DictOdsSql.ods_dict_delete_d_a_spark.replaceAll("\\$ODS_TABLE_NAME\\$", ods_table)
                .replaceAll("\\$EBS_TABLE_NAME\\$", ebs_table);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>删除Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(sparkSql);
        System.out.println("\r\n");
        return sparkSql;
    }

    @Override
    public String getObsDictDASparkSql(String item, String jsonString) {
        // 获取增量表名
        String ods_d_i_table = this.getOdsDITableName(item);

        // 获取删除表名
        String ods_delete_d_a_table = this.getOdsDDTableName(item);

        // 获取全量表名
        String ods_d_a_table = this.getOdsDATableName(item);

        // 获取字段
        DASparkSqlPart daSparkSqlPart = this.getObsDASparkSqlPart(jsonString);

        // 生成sql
        String sparkSql = DictOdsSql.ods_dict_d_a_spark.replaceAll("\\$SELECT_COLUMNS_V\\$", daSparkSqlPart.select_columns_v)
                .replaceAll("\\$ODS_DA_TABLE\\$", ods_d_a_table).replaceAll("\\$ODS_DI_TABLE\\$", ods_d_i_table)
                .replaceAll("\\$ODS_DD_TABLE\\$", ods_delete_d_a_table).replaceAll("\\$SELECT_COLUMNS\\$", daSparkSqlPart.select_columns);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>全量Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(sparkSql);
        System.out.println("\r\n");
        return sparkSql;
    }

    private DiSparkSqlPart getObsDiSparkSqlPart(String jsonString) {
        DiSparkSqlPart diSparkSqlPart = new DiSparkSqlPart();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        StringBuffer select_columns = new StringBuffer();
        StringBuffer json_columns = new StringBuffer();
        StringBuffer json_as_columns = new StringBuffer();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("LastUpdateTime") || entry.getKey().equals("ID")) {
                continue;
            }
            String fieldUpper = entry.getKey().replaceAll("\\.", "_");
            String fieldLower = fieldUpper.toLowerCase();
            select_columns.append("\tb.").append(fieldLower).append(",").append("\r\n");
            json_columns.append("\t'").append(entry.getKey()).append("',").append("\r\n");
            json_as_columns.append("\t").append(fieldLower).append(",").append("\r\n");
        }
        // 去除最后一个逗号
        String json_columns_string = json_columns.toString();
        json_columns_string = json_columns_string.substring(0, json_columns_string.lastIndexOf(","));
        String json_as_columns_string = json_as_columns.toString();
        json_as_columns_string = json_as_columns_string.substring(0, json_as_columns_string.lastIndexOf(","));
        diSparkSqlPart.select_columns = select_columns.toString();
        diSparkSqlPart.json_columns = json_columns_string;
        diSparkSqlPart.json_as_columns = json_as_columns_string;
        return diSparkSqlPart;
    }

    private DASparkSqlPart getObsDASparkSqlPart(String jsonString) {
        DASparkSqlPart daSparkSqlPart = new DASparkSqlPart();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        StringBuffer select_columns_v = new StringBuffer();
        StringBuffer select_columns = new StringBuffer();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getKey().equals("LastUpdateTime") || entry.getKey().equals("ID")) {
                continue;
            }
            String field = entry.getKey().replaceAll("\\.", "_").toLowerCase();
            select_columns_v.append(field).append(",");
            select_columns.append("a.").append(field).append(",");
        }
        // select_columns_v 添加 tenant_id 和 op_time
        select_columns_v.append("tenant_id,op_time");
        // select_columns 添加 tenant_id
        select_columns.append("a.tenant_id");
        daSparkSqlPart.select_columns_v = select_columns_v.toString();
        daSparkSqlPart.select_columns = select_columns.toString();
        return daSparkSqlPart;
    }

    /**
     * 增量表sql 部分
     */
    @Data
    public static class DiSparkSqlPart {
        /**
         * select filed 带前缀b.
         */
        private String select_columns;

        /**
         * 带单引号
         */
        private String json_columns;

        /**
         * 别名 以逗号分隔
         */
        private String json_as_columns;
    }

    /**
     * 全量表sql 部分
     */
    @Data
    public static class DASparkSqlPart {
        /**
         * 增量表字段
         */
        private String select_columns_v;

        /**
         * 增量表字段带 a.
         */
        private String select_columns;
    }
}
