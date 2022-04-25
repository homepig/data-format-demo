package com.example.dataformatdemo;

import java.io.File;

/**
 * 产值计划
 */
public class OutputValuePlan {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "产值计划ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "产值计划dwd.xls";

        // dws层
        String dwsFilePath = "";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("output_value_plan", "产值计划");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"YSMXs\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"产值计划\",\n" +
                "\t\"TJNF\": 2022,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": \"本月无报量计划\",\n" +
                "\t\"master_bill_id\": 1018702,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"LastUpdateTime\": \"2022-04-25 00:36:37\",\n" +
                "\t\"Dept.DeptId\": 3389,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"ID\": 1018702,\n" +
                "\t\"DeptName\": \"蓝山上湾4#、5#、17#A、17#B、18#ABC楼南区地下室2区\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"id\": 1018702,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"org_name\": \"蓝山上湾4#、5#、17#A、17#B、18#ABC楼南区地下室2区\",\n" +
                "\t\"BizDate\": \"2022-04-30 00:00:00\",\n" +
                "\t\"HasAttach\": false,\n" +
                "\t\"HT.ID\": 1006326,\n" +
                "\t\"TJYF\": \"4月\",\n" +
                "\t\"CreateTime\": \"2022-04-24 10:30:14\",\n" +
                "\t\"GUID\": \"9d63878c-05b1-409f-8c8d-b077df17edbb\",\n" +
                "\t\"PID\": 0,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1018702,\n" +
                "\t\"Code\": \"GXJGWJ-CZJH20220424002\",\n" +
                "\t\"Effective\": true,\n" +
                "\t\"last_update_time\": \"2022-04-25 00:36:37\",\n" +
                "\t\"ModuleCode\": \"GEPS.Contract.SRCBGL.CZJHModule\",\n" +
                "\t\"HZMXs\": null,\n" +
                "\t\"State\": \"已提交\",\n" +
                "\t\"org_id\": 3389,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"JE\": 0.0,\n" +
                "\t\"CreatorName\": \"李锐\",\n" +
                "\t\"JHBZFS\": \"金额汇总\"\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("output_value_plan", "产值计划", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("output_value_plan", "产值计划", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("produce_prod_value_plan", "产值计划", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("produce_prod_value_plan", dwdFilePath, "ods_geps_meta_output_value_plan_d_a", "");
        }

        // dws 建模 及 spark sql
        if (dwsFilePath != null && !"".equals(dwsFilePath)) {
            dictBaseDataUtil.dwsCreateDDL("", "", dwsFilePath);
        }
    }
}
