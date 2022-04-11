package com.example.dataformatdemo;

import java.io.File;

/**
 * 材料付款申请单
 */
public class MaterialPayApplication {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "材料付款申请单ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "材料付款申请单dwd.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("material_pay_application", "材料付款申请单");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"JSQK\": \"已结算\",\n" +
                "\t\"JSMXs\": null,\n" +
                "\t\"SLHY\": true,\n" +
                "\t\"TBZT\": \"未同步\",\n" +
                "\t\"Remark\": null,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"KZFJEAHT\": 70000.0,\n" +
                "\t\"LJPFJEAFKLX\": 90144451.38,\n" +
                "\t\"LJDKJEAFKLX\": 0.0,\n" +
                "\t\"JBR\": null,\n" +
                "\t\"JGCSSFSX\": false,\n" +
                "\t\"ID\": 1167671,\n" +
                "\t\"id\": 1167671,\n" +
                "\t\"SQSM\": null,\n" +
                "\t\"BizDate\": \"2021-12-16 09:29:49\",\n" +
                "\t\"KHYX\": \"中国建设银行股份有限公司重庆铜梁龙门支行\",\n" +
                "\t\"HT.ID\": 1317327,\n" +
                "\t\"DKJE\": 0.0,\n" +
                "\t\"HTZFBL\": 100.0,\n" +
                "\t\"LJJSJE\": 111084.0,\n" +
                "\t\"WBHL\": 0.0,\n" +
                "\t\"Code\": \"GXJGQJ-002-CLFKSQD-202112-0136\",\n" +
                "\t\"TimeStamp\": \"1649354727073\",\n" +
                "\t\"SFJHN\": true,\n" +
                "\t\"last_update_time\": \"2022-04-08 02:05:27\",\n" +
                "\t\"LJJHJEAHT\": 110500.0,\n" +
                "\t\"LJDKJEAHT\": 0.0,\n" +
                "\t\"TBSJ2\": null,\n" +
                "\t\"YQDHT\": true,\n" +
                "\t\"ZFJSJS_TITLE\": \"本期结算金额\",\n" +
                "\t\"ZJFKBL\": 99.47,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"SQRQ\": \"2021-12-16 00:00:00\",\n" +
                "\t\"SQJE\": 70000.0,\n" +
                "\t\"ZJFKBLAPF\": 99.47,\n" +
                "\t\"YZF\": true,\n" +
                "\t\"YWSXBS\": true,\n" +
                "\t\"HTFKBL\": 100.0,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"WBBZ\": null,\n" +
                "\t\"LHH\": \"105653016029\",\n" +
                "\t\"FKHZHYE\": 74940798.92,\n" +
                "\t\"LJJHJEAFGS\": 110500.0,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"WBSQJE\": 0.0,\n" +
                "\t\"KJYFKJE\": 0.0,\n" +
                "\t\"LJDKJEAFGS\": 0.0,\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"融创南凌樾三期1-18#楼\",\n" +
                "\t\"KZFJEAFGS\": 70000.0,\n" +
                "\t\"BCHTYFJE\": 70584.0,\n" +
                "\t\"HTZFLX\": \"结算款\",\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"LJJXPJE\": 111084.0,\n" +
                "\t\"LJDKJE\": 0.0,\n" +
                "\t\"HTJE\": 600000.0,\n" +
                "\t\"CreateTime\": \"2021-12-16 09:29:49\",\n" +
                "\t\"FKSQCHTYFGKBS\": true,\n" +
                "\t\"ZFMXs\": null,\n" +
                "\t\"DJTBZT2\": null,\n" +
                "\t\"PID\": 0,\n" +
                "\t\"DJTBZT1\": null,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"ModuleCode\": \"GEPS.Material.Purchase.CLFKSQDModule\",\n" +
                "\t\"LJPFJEAHT\": 40500.0,\n" +
                "\t\"PZH\": null,\n" +
                "\t\"PFZE\": 110500.0,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"LJJHJEAFKLX\": 94379754.16,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"SYFK\": false,\n" +
                "\t\"FKSQDJZSPID\": null,\n" +
                "\t\"AHTYFJE\": 70584.0,\n" +
                "\t\"AHTYFZT\": \"低于合同应付584.00元\",\n" +
                "\t\"Name\": null,\n" +
                "\t\"master_bill_id\": 1167671,\n" +
                "\t\"FKZE\": 110500.0,\n" +
                "\t\"ZHYE\": 75010798.92,\n" +
                "\t\"PFJE\": 70000.0,\n" +
                "\t\"FKHKYYE\": 74863798.92,\n" +
                "\t\"LastUpdateTime\": \"2022-04-08 02:05:27\",\n" +
                "\t\"SFCZTBDCW\": false,\n" +
                "\t\"LJFKBLAPF\": 36.46,\n" +
                "\t\"LJPFJEAFGS\": 40500.0,\n" +
                "\t\"ZJZFLXSZZD.ID\": 100002,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"YXZH\": \"50001196200052500421\",\n" +
                "\t\"GUID\": \"0b40ab30-e1fd-4a35-832a-b4365a49ca4a\",\n" +
                "\t\"LJFKBL\": 36.46,\n" +
                "\t\"FKSQDJZSPBH\": null,\n" +
                "\t\"LJJSQFJE\": 70584.0,\n" +
                "\t\"GSKHYHJZH\": null,\n" +
                "\t\"TBZT2\": null,\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 27376,\n" +
                "\t\"HTLX\": \"GEPS.Material.Purchase.CLCGHT\",\n" +
                "\t\"GSFKSQDJZSPBH\": null,\n" +
                "\t\"CreatorName\": \"汪精忠\",\n" +
                "\t\"AHTYFZTBS\": false,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"KZFJEAFKLX\": 4235302.78,\n" +
                "\t\"ModuleName\": \"材料付款申请单\",\n" +
                "\t\"LJPFJE\": 40500.0,\n" +
                "\t\"ZFFS\": \"电汇\",\n" +
                "\t\"DKZE\": 0.0,\n" +
                "\t\"SyncState\": null,\n" +
                "\t\"HZHB.ID\": 1355534,\n" +
                "\t\"Dept.DeptId\": 27376,\n" +
                "\t\"SFGKZHYE\": true,\n" +
                "\t\"KYYE\": 74933798.92,\n" +
                "\t\"LJFKJE\": 40500.0,\n" +
                "\t\"DeptName\": \"融创南凌樾三期1-18#楼\",\n" +
                "\t\"FJZS\": 0,\n" +
                "\t\"HasAttach\": true,\n" +
                "\t\"LJLYJE\": 163830.0,\n" +
                "\t\"FKJE\": 70000.0,\n" +
                "\t\"FPMXs\": null,\n" +
                "\t\"FKRQ\": \"2022-04-06 00:00:00\",\n" +
                "\t\"DFXTDJBH\": null,\n" +
                "\t\"GSFKSQDJZSPID\": null,\n" +
                "\t\"DJTBSJ2\": null,\n" +
                "\t\"DJTBSJ1\": null,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1167671,\n" +
                "\t\"ZFJSJS\": \"BQJSJE\",\n" +
                "\t\"BGHHTJE\": 600000.0,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("material_pay_application", "材料付款申请单", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("material_pay_application", "材料付款申请单", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("fn_material_pay_application", "材料付款申请单", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("fn_material_pay_application", dwdFilePath, "ods_geps_meta_material_pay_application_d_a", "");
        }
    }
}
