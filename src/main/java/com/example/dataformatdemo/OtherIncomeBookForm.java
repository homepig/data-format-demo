package com.example.dataformatdemo;

import java.io.File;

/**
 * 其他收入记账单
 */
public class OtherIncomeBookForm {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "其他收入记账单ods.xlsx";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "其他收入记账单dwd.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("other_income_book_form", "其他收入记账单");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"TBZT\": \"未同步\",\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": \"韩彦君存入款，用于支付履约保函保证金及担保费\",\n" +
                "\t\"master_bill_id\": 1023923,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"LastUpdateTime\": \"2022-04-07 02:08:04\",\n" +
                "\t\"SourceObjKey\": null,\n" +
                "\t\"ID\": 1023923,\n" +
                "\t\"id\": 1023923,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"BizDate\": \"2022-03-11 00:00:00\",\n" +
                "\t\"HT.ID\": null,\n" +
                "\t\"HSJE\": 855780.0,\n" +
                "\t\"GUID\": \"3424d458-bace-46d8-ab40-6fd9a4569ba5\",\n" +
                "\t\"Code\": \"GXJGYIJ-C1011022022001-QTSRJZD-202204-0002\",\n" +
                "\t\"last_update_time\": \"2022-04-07 02:08:04\",\n" +
                "\t\"TBSJ2\": null,\n" +
                "\t\"GSKHYHJZH\": null,\n" +
                "\t\"TBZT2\": \"未同步\",\n" +
                "\t\"State\": \"已提交\",\n" +
                "\t\"org_id\": 114478,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"JE\": 855780.0,\n" +
                "\t\"CreatorName\": \"潘耀武\",\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"HSDX.ID\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"其他收入记账单\",\n" +
                "\t\"SE\": 0.0,\n" +
                "\t\"QTSRJZDFYMXs\": null,\n" +
                "\t\"TaxRatio\": 0.0,\n" +
                "\t\"Dept.DeptId\": 114478,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"SourceSysKey\": null,\n" +
                "\t\"DeptName\": \"中央储备粮赤峰直属库有限公司粮食仓储物流项目\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"中央储备粮赤峰直属库有限公司粮食仓储物流项目\",\n" +
                "\t\"HasAttach\": false,\n" +
                "\t\"QTSRJZDFPMXs\": null,\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"DFXTDJBH\": null,\n" +
                "\t\"CreateTime\": \"2022-04-06 01:15:05\",\n" +
                "\t\"DJTBZT2\": \"未同步\",\n" +
                "\t\"DJTBSJ2\": null,\n" +
                "\t\"PID\": 0,\n" +
                "\t\"DJTBSJ1\": null,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1023923,\n" +
                "\t\"DJTBZT1\": \"未同步\",\n" +
                "\t\"Effective\": true,\n" +
                "\t\"WLDW.ID\": 1028206,\n" +
                "\t\"ModuleCode\": \"GEPS.Capital.ZJWL.QTSRJZDModule\",\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("other_income_book_form", "其他收入记账单", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("other_income_book_form", "其他收入记账单", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("fn_other_income_book_form", "其他收入记账单", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("fn_other_income_book_form", dwdFilePath, "ods_geps_meta_other_income_book_form_d_a", "");
        }
    }
}
