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
        // ?????????????????????
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
        comments.put("ywlx", "????????????");
        comments.put("ytlx", "????????????");
        comments.put("code", "??????");
        comments.put("jianpin", "??????");
        comments.put("name", "??????");
        comments.put("remark", "??????");
        comments.put("master_bill_id", "??????ID");
        comments.put("lastupdateuser", "???????????????");
        comments.put("last_update_time", "??????????????????");
        comments.put("org_id", "??????ID");
        comments.put("dept_deptid", "????????????ID");
        comments.put("pinyin", "??????");
        comments.put("creator_id", "?????????ID");
        comments.put("creator_name", "?????????");
        comments.put("id", "ID");
        comments.put("data_status", "????????????");
        comments.put("org_name", "????????????");
        comments.put("fq", "??????");
        comments.put("category_id", "??????ID");
        comments.put("isleaf", "??????????????????");
        comments.put("orderno", "?????????");
        comments.put("fullcode", "?????????");
        comments.put("xtbs", "????????????");
        comments.put("htlx", "????????????");
        comments.put("issystem", "??????????????????");
        comments.put("level", "??????");
        comments.put("pid", "???id");
        comments.put("htflsx", "??????????????????");
        comments.put("htflsx_id", "??????????????????ID");
        return comments;
    }

    private Map<String, String> getColumnComments(String filePath) {
        Map<String, String> comments = new LinkedHashMap<>();
        EasyExcel.read(filePath, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                String code = excelData.getCode();
                String desc = excelData.getDesc();
                if (code != null && desc != null && !"".equals(desc)) {
                    desc = desc.replaceAll("???","").replaceAll("\\(","")
                            .replaceAll("???","").replaceAll("\\)","")
                            .replaceAll(":","").replaceAll("???","")
                            .replaceAll(",","???").replaceAll("???","???")
                            .replaceAll("%","?????????");
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
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods????????????<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDISql(String item, String tableComment, String jsonString, String odsFilePath) {
        String ods_dict_d_i_table = this.getOdsDITableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString, odsFilePath);
        String create_table_sql = DictOdsSql.ods_dict_d_i.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods????????????<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDeleteDASql(String item, String tableComment) {
        String ods_dict_delete_d_a = this.getOdsDDTableName(item);
        String create_table_sql = DictOdsSql.ods_dict_delete_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_delete_d_a).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods????????????<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDASql(String item, String tableComment, String jsonString) {
        String ods_dict_d_i_table = this.getOdsDATableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString);
        String create_table_sql = DictOdsSql.ods_dict_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods????????????<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDASql(String item, String tableComment, String jsonString, String odsFilePath) {
        String ods_dict_d_i_table = this.getOdsDATableName(item);
        String columns = this.getFieldsSqlFromJsonString(jsonString, odsFilePath);
        String create_table_sql = DictOdsSql.ods_dict_d_a.replaceAll("\\$TABLE_NAME\\$", ods_dict_d_i_table).replaceAll("\\$COLUMNS\\$", columns).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>ods????????????<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_table_sql);
        System.out.println("\r\n");
        return create_table_sql;
    }

    @Override
    public String getObsDictDISparkSql(String item, String jsonString) {
        // ??????ebs??????
        String ebs_table = dictBaseDataModuleEbsService.getEbsDITableName(item);

        // ?????? obs ??????
        String obs_table = this.getOdsDITableName(item);

        DiSparkSqlPart diSparkSqlPart = this.getObsDiSparkSqlPart(jsonString);

        String sparkSql = DictOdsSql.ods_dict_d_i_spark.replaceAll("\\$ODS_TABLE_NAME\\$", obs_table)
                .replaceAll("\\$EBS_TABLE_NAME\\$", ebs_table).replaceAll("\\$SELECT_COLUMNS\\$", diSparkSqlPart.select_columns)
                .replaceAll("\\$JSON_COLUMNS\\$", diSparkSqlPart.json_columns).replaceAll("\\$JSON_AS_COLUMNS\\$", diSparkSqlPart.json_as_columns);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>??????Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(sparkSql);
        System.out.println("\r\n");
        return sparkSql;
    }

    @Override
    public String getObsDictDdSparkSql(String item) {
        // ??????ebs??????
        String ebs_table = dictBaseDataModuleEbsService.getEbsDATableName(item);

        // ??????ods??????
        String ods_table = this.getOdsDDTableName(item);
        String sparkSql = DictOdsSql.ods_dict_delete_d_a_spark.replaceAll("\\$ODS_TABLE_NAME\\$", ods_table)
                .replaceAll("\\$EBS_TABLE_NAME\\$", ebs_table);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>??????Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(sparkSql);
        System.out.println("\r\n");
        return sparkSql;
    }

    @Override
    public String getObsDictDASparkSql(String item, String jsonString) {
        // ??????????????????
        String ods_d_i_table = this.getOdsDITableName(item);

        // ??????????????????
        String ods_delete_d_a_table = this.getOdsDDTableName(item);

        // ??????????????????
        String ods_d_a_table = this.getOdsDATableName(item);

        // ????????????
        DASparkSqlPart daSparkSqlPart = this.getObsDASparkSqlPart(jsonString);

        // ??????sql
        String sparkSql = DictOdsSql.ods_dict_d_a_spark.replaceAll("\\$SELECT_COLUMNS_V\\$", daSparkSqlPart.select_columns_v)
                .replaceAll("\\$ODS_DA_TABLE\\$", ods_d_a_table).replaceAll("\\$ODS_DI_TABLE\\$", ods_d_i_table)
                .replaceAll("\\$ODS_DD_TABLE\\$", ods_delete_d_a_table).replaceAll("\\$SELECT_COLUMNS\\$", daSparkSqlPart.select_columns);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>??????Spark SQL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
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
        // ????????????????????????
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
        // select_columns_v ?????? tenant_id ??? op_time
        select_columns_v.append("tenant_id,op_time");
        // select_columns ?????? tenant_id
        select_columns.append("a.tenant_id");
        daSparkSqlPart.select_columns_v = select_columns_v.toString();
        daSparkSqlPart.select_columns = select_columns.toString();
        return daSparkSqlPart;
    }

    /**
     * ?????????sql ??????
     */
    @Data
    public static class DiSparkSqlPart {
        /**
         * select filed ?????????b.
         */
        private String select_columns;

        /**
         * ????????????
         */
        private String json_columns;

        /**
         * ?????? ???????????????
         */
        private String json_as_columns;
    }

    /**
     * ?????????sql ??????
     */
    @Data
    public static class DASparkSqlPart {
        /**
         * ???????????????
         */
        private String select_columns_v;

        /**
         * ?????????????????? a.
         */
        private String select_columns;
    }
}
