package com.example.dataformatdemo;

import java.io.File;

/**
 * 项目借款
 */
public class ProjectBorrowMoney {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "项目借款ods.xlsx";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "项目借款dwd.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("project_borrow_money", "项目借款");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"HTGQT\": 1,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"JKQX\": null,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"TBZT\": null,\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": \"公司付广西检验检疫局项目借款\",\n" +
                "\t\"master_bill_id\": 1000112,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"XMJBXX.ID\": 1000439,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"JKJEWY\": 398.7,\n" +
                "\t\"LastUpdateTime\": \"2020-11-24 15:36:33\",\n" +
                "\t\"JBR\": null,\n" +
                "\t\"ID\": 1000112,\n" +
                "\t\"id\": 1000112,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"BizDate\": \"2020-11-20 21:53:10\",\n" +
                "\t\"JKLXWY\": 0.0,\n" +
                "\t\"GUID\": \"901271f9-0968-4374-aa61-771918f889e9\",\n" +
                "\t\"SGXMB\": \"南宁海关技术业务用房\",\n" +
                "\t\"JKJSSJ\": null,\n" +
                "\t\"Code\": \"GXJGSANJ-XMJK20201120215423012\",\n" +
                "\t\"last_update_time\": \"2020-11-24 15:36:33\",\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 1138,\n" +
                "\t\"JKQSSJ\": \"2015-08-28 00:00:00\",\n" +
                "\t\"SGHTDJ.ID\": 1000020,\n" +
                "\t\"HTZJWY\": 15397.330712,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"JKYT\": \"公司付广西检验检疫局项目借款\",\n" +
                "\t\"CreatorName\": \"李琪琪\",\n" +
                "\t\"SQDW.DeptId\": 1138,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"项目借款\",\n" +
                "\t\"Dept.DeptId\": 1138,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"YCXFYWY\": 0.0,\n" +
                "\t\"DeptName\": \"南宁海关技术业务用房\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"南宁海关技术业务用房\",\n" +
                "\t\"XMJL\": null,\n" +
                "\t\"HasAttach\": false,\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"CreateTime\": \"2020-11-20 21:53:10\",\n" +
                "\t\"XMLX\": null,\n" +
                "\t\"PID\": 0,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1000112,\n" +
                "\t\"JKBJWY\": 398.7,\n" +
                "\t\"RZLL\": 0.0,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"RZLX\": null,\n" +
                "\t\"ModuleCode\": \"GXJG.XMJKMK.XMJKModule\",\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"XMSJBHS\": 0.0\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("project_borrow_money", "项目借款", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("project_borrow_money", "项目借款", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("fn_project_borrow_money", "项目借款", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("fn_project_borrow_money", dwdFilePath, "ods_geps_meta_project_borrow_money_d_a", "");
        }
    }
}
