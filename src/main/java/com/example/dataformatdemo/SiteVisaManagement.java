package com.example.dataformatdemo;

import java.io.File;

/**
 * 现场签证管理
 */
public class SiteVisaManagement {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "现场签证管理ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "现场签证管理dwd.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("site_visa_management", "现场签证管理");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"QZBH\": null,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"QZZY\": \"无签证工程量\",\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": null,\n" +
                "\t\"master_bill_id\": 1009245,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"PFJE\": 0.0,\n" +
                "\t\"LastUpdateTime\": \"2020-06-05 16:04:01\",\n" +
                "\t\"YS\": 0,\n" +
                "\t\"JBR\": \"陈佳\",\n" +
                "\t\"ID\": 1009245,\n" +
                "\t\"id\": 1009245,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"BizDate\": null,\n" +
                "\t\"HT.ID\": 1015686,\n" +
                "\t\"BCJE\": 0.0,\n" +
                "\t\"JJBG\": false,\n" +
                "\t\"GUID\": \"314b6ac0-5350-48c0-a9df-0f1e34680f30\",\n" +
                "\t\"Code\": \"GXJGYA-2F-ZL19-XCQZ-202001-001\",\n" +
                "\t\"YXZT\": false,\n" +
                "\t\"last_update_time\": \"2020-06-05 16:04:01\",\n" +
                "\t\"State\": \"已提交\",\n" +
                "\t\"org_id\": 10336,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"SSZT\": null,\n" +
                "\t\"CreatorName\": \"陈  佳\",\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"现场签证管理\",\n" +
                "\t\"FFJLs\": null,\n" +
                "\t\"Dept.DeptId\": 10336,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"QZDW\": null,\n" +
                "\t\"DeptName\": \"中粮崇左2019年维修项目\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"中粮崇左2019年维修项目\",\n" +
                "\t\"HasAttach\": false,\n" +
                "\t\"TJJY\": false,\n" +
                "\t\"CreateTime\": \"2020-01-11 22:31:35\",\n" +
                "\t\"PID\": 0,\n" +
                "\t\"LastUpdateUserName\": \"管理员\",\n" +
                "\t\"RID\": 1009245,\n" +
                "\t\"FSRQ\": \"2020-01-11 00:00:00\",\n" +
                "\t\"XCQZID\": 0,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"JYNR\": \"无签证\",\n" +
                "\t\"ModuleCode\": \"GEPS.Contract.SGHT.XCQZGLModule\",\n" +
                "\t\"SDRQ\": null,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("site_visa_management", "现场签证管理", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("site_visa_management", "现场签证管理", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("produce_site_visa_management", "现场签证管理", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("produce_site_visa_management", dwdFilePath, "ods_geps_meta_site_visa_management_d_a", "");
        }
    }
}
