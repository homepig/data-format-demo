package com.example.dataformatdemo;

import java.io.File;

public class OutputValueStatisticsDemo {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "产值统计.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("output_value_statistics", "产值统计");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"TJNF\": 2022,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": null,\n" +
                "\t\"master_bill_id\": 1044172,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"LastUpdateTime\": \"2022-04-06 03:01:39\",\n" +
                "\t\"EP_sWGCBM\": null,\n" +
                "\t\"LJJE\": 277479000.0,\n" +
                "\t\"ID\": 1044172,\n" +
                "\t\"id\": 1044172,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"BizDate\": \"2022-03-30 00:00:00\",\n" +
                "\t\"HT.ID\": 1009887,\n" +
                "\t\"EP_sWGCMC\": null,\n" +
                "\t\"EP_JG\": null,\n" +
                "\t\"TJYF\": \"3月\",\n" +
                "\t\"GUID\": \"56366ddb-bf8c-4e21-af2f-ca104c7b2cc9\",\n" +
                "\t\"Code\": \"GXJGZCB-2022年3月份产值\",\n" +
                "\t\"last_update_time\": \"2022-04-06 03:01:39\",\n" +
                "\t\"State\": \"已提交\",\n" +
                "\t\"org_id\": 11609,\n" +
                "\t\"EP_BYJGCZ\": 8000000.0,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"BZFS\": \"预算明细\",\n" +
                "\t\"XMSSGS.DeptId\": 11582,\n" +
                "\t\"JE\": 8000000.0,\n" +
                "\t\"LJXXSE\": 0.0,\n" +
                "\t\"CreatorName\": \"卢文清\",\n" +
                "\t\"WSJE\": 8000000.0,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"YSMXs\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"EffectiveTime\": null,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"产值统计\",\n" +
                "\t\"EP_SGJD\": null,\n" +
                "\t\"TaxRatio\": 0.0,\n" +
                "\t\"EP_WGRQ\": null,\n" +
                "\t\"Dept.DeptId\": 11609,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"DeptName\": \"中国-东盟医疗保健合作中心（广西）项目工程\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"中国-东盟医疗保健合作中心（广西）项目工程\",\n" +
                "\t\"HasAttach\": true,\n" +
                "\t\"EP_BQXXJD\": null,\n" +
                "\t\"CreateTime\": \"2022-03-24 09:55:21\",\n" +
                "\t\"PID\": 0,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1044172,\n" +
                "\t\"EP_BYJGMJ\": 0.0,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"ModuleCode\": \"GEPS.Contract.SRCBGL.CZTJModule\",\n" +
                "\t\"EP_TGRQ\": null,\n" +
                "\t\"EP_XMZT\": \"在建\",\n" +
                "\t\"EP_CS\": null,\n" +
                "\t\"EP_SGMJ\": 0.0,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"XXSE\": 0.0,\n" +
                "\t\"LJWSJE\": 277479000.0\n" +
                "}";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("output_value_statistics", "产值统计", jsonString, odsFilePath);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("output_value_statistics", "产值统计", dimFilePath, jsonString);
        }
    }
}
