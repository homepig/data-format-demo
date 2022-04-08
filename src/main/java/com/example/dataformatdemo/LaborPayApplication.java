package com.example.dataformatdemo;

import java.io.File;

/**
 * 劳务分包付款申请单
 */
public class LaborPayApplication {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "劳务分包付款申请单ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "劳务分包付款申请单dwd.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("labor_pay_application", "劳务分包付款申请单");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"JSQK\": \"已结算\",\n" +
                "\t\"JSMXs\": null,\n" +
                "\t\"SLHY\": false,\n" +
                "\t\"TBZT\": \"未同步\",\n" +
                "\t\"Remark\": \"塔司信号工2022.1-2月工人工资，总包专户代发\",\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"KZFJEAHT\": 7843281.14,\n" +
                "\t\"LJPFJEAFKLX\": 57630641.3,\n" +
                "\t\"LJDKJEAFKLX\": 0.0,\n" +
                "\t\"JBR\": null,\n" +
                "\t\"JGCSSFSX\": false,\n" +
                "\t\"ID\": 1132514,\n" +
                "\t\"id\": 1132514,\n" +
                "\t\"SQSM\": null,\n" +
                "\t\"BizDate\": \"2022-03-29 16:34:55\",\n" +
                "\t\"KHYX\": \"中国银行眉山仁寿支行\",\n" +
                "\t\"HT.ID\": 1198359,\n" +
                "\t\"DKJE\": 0.0,\n" +
                "\t\"HTZFBL\": 85.0,\n" +
                "\t\"LJJSJE\": 32634329.69,\n" +
                "\t\"WBHL\": 0.0,\n" +
                "\t\"Code\": \"GXJGQJ-002-LWFBFKSQD-202203-0103\",\n" +
                "\t\"TimeStamp\": \"1649354944226\",\n" +
                "\t\"SFJHN\": true,\n" +
                "\t\"last_update_time\": \"2022-04-08 02:09:04\",\n" +
                "\t\"LJJHJEAHT\": 33585171.74,\n" +
                "\t\"LJDKJEAHT\": 0.0,\n" +
                "\t\"TBSJ2\": null,\n" +
                "\t\"YQDHT\": true,\n" +
                "\t\"ZFJSJS_TITLE\": \"开累结算金额\",\n" +
                "\t\"ZJFKBL\": 55.31,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"SQRQ\": \"2022-03-29 00:00:00\",\n" +
                "\t\"SQJE\": 42590.82,\n" +
                "\t\"ZJFKBLAPF\": 78.94,\n" +
                "\t\"YZF\": true,\n" +
                "\t\"YWSXBS\": true,\n" +
                "\t\"HTFKBL\": 85.0,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"WBBZ\": null,\n" +
                "\t\"LHH\": null,\n" +
                "\t\"FKHZHYE\": 74940798.92,\n" +
                "\t\"LJJHJEAFGS\": 67929968.74,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"WBSQJE\": 0.0,\n" +
                "\t\"KJYFKJE\": 0.0,\n" +
                "\t\"LJDKJEAFGS\": 0.0,\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"融创南凌樾三期1-18#楼\",\n" +
                "\t\"KZFJEAFGS\": 17388806.14,\n" +
                "\t\"BCHTYFJE\": 9730889.63,\n" +
                "\t\"HTZFLX\": \"春节人工费支付\",\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"LJJXPJE\": 5000000.0,\n" +
                "\t\"LJDKJE\": 0.0,\n" +
                "\t\"HTJE\": 33000000.0,\n" +
                "\t\"CreateTime\": \"2022-03-29 16:34:55\",\n" +
                "\t\"FKSQCHTYFGKBS\": true,\n" +
                "\t\"ZFMXs\": null,\n" +
                "\t\"DJTBZT2\": null,\n" +
                "\t\"PID\": 0,\n" +
                "\t\"DJTBZT1\": null,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"ModuleCode\": \"GEPS.Labor.Contract.LWFBFKSQDModule\",\n" +
                "\t\"LJPFJEAHT\": 25741890.6,\n" +
                "\t\"PZH\": null,\n" +
                "\t\"PFZE\": 25760681.42,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"LJJHJEAFKLX\": 77097236.04,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"SYFK\": false,\n" +
                "\t\"FKSQDJZSPID\": null,\n" +
                "\t\"AHTYFJE\": 27739180.23,\n" +
                "\t\"AHTYFZT\": \"低于合同应付9,688,298.81元\",\n" +
                "\t\"Name\": null,\n" +
                "\t\"master_bill_id\": 1132514,\n" +
                "\t\"FKZE\": 18050881.42,\n" +
                "\t\"ZHYE\": 74983389.74,\n" +
                "\t\"PFJE\": 42590.82,\n" +
                "\t\"FKHKYYE\": 74863798.92,\n" +
                "\t\"LastUpdateTime\": \"2022-04-08 02:09:04\",\n" +
                "\t\"SFCZTBDCW\": false,\n" +
                "\t\"LJFKBLAPF\": 78.81,\n" +
                "\t\"LJPFJEAFGS\": 50541162.6,\n" +
                "\t\"ZJZFLXSZZD.ID\": 100001,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"YXZH\": \"121269572240\",\n" +
                "\t\"GUID\": \"b0254ffd-b624-4a8c-9bf9-625e156b6883\",\n" +
                "\t\"LJFKBL\": 55.18,\n" +
                "\t\"FKSQDJZSPBH\": null,\n" +
                "\t\"LJJSQFJE\": 6916239.09,\n" +
                "\t\"GSKHYHJZH\": null,\n" +
                "\t\"TBZT2\": null,\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 27376,\n" +
                "\t\"HTLX\": \"GEPS.Labor.Contract.LWFBHTDJ\",\n" +
                "\t\"GSFKSQDJZSPBH\": null,\n" +
                "\t\"CreatorName\": \"汪精忠\",\n" +
                "\t\"AHTYFZTBS\": false,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"KZFJEAFKLX\": 19466594.74,\n" +
                "\t\"ModuleName\": \"劳务分包付款申请单\",\n" +
                "\t\"LJPFJE\": 25718090.6,\n" +
                "\t\"ZFFS\": \"其他\",\n" +
                "\t\"DKZE\": 0.0,\n" +
                "\t\"SyncState\": null,\n" +
                "\t\"HZHB.ID\": 1185300,\n" +
                "\t\"Dept.DeptId\": 27376,\n" +
                "\t\"SFGKZHYE\": true,\n" +
                "\t\"KYYE\": 74906389.74,\n" +
                "\t\"LJFKJE\": 18008290.6,\n" +
                "\t\"DeptName\": \"融创南凌樾三期1-18#楼\",\n" +
                "\t\"FJZS\": 0,\n" +
                "\t\"HasAttach\": true,\n" +
                "\t\"LJLYJE\": 32634329.69,\n" +
                "\t\"FKJE\": 42590.82,\n" +
                "\t\"FPMXs\": null,\n" +
                "\t\"FKRQ\": \"2022-04-06 00:00:00\",\n" +
                "\t\"DFXTDJBH\": null,\n" +
                "\t\"GSFKSQDJZSPID\": null,\n" +
                "\t\"DJTBSJ2\": null,\n" +
                "\t\"DJTBSJ1\": null,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1132514,\n" +
                "\t\"ZFJSJS\": \"KLJSJE\",\n" +
                "\t\"BGHHTJE\": 70000000.0,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("labor_pay_application", "劳务分包付款申请单", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("labor_pay_application", "劳务分包付款申请单", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("fn_labor_pay_application", "劳务分包付款申请单", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("fn_labor_pay_application", dwdFilePath, "ods_geps_meta_labor_pay_application_d_a", "");
        }
    }
}
