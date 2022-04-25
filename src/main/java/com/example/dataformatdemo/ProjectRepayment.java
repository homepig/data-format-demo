package com.example.dataformatdemo;

import java.io.File;

/**
 * 项目还款
 */
public class ProjectRepayment {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "";

        // dwd层
        String dwdFilePath = "";

        // dws层
        String dwsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "项目还款dws.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("project_repayment", "项目还款");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"JKQX\": null,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"Name\": null,\n" +
                "\t\"Remark\": \"直管南宁公司还广西检疫局项目借款\",\n" +
                "\t\"master_bill_id\": 1000064,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"JKJEWY\": 0.0,\n" +
                "\t\"LastUpdateTime\": \"2020-11-24 15:36:38\",\n" +
                "\t\"ID\": 1000064,\n" +
                "\t\"id\": 1000064,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"BizDate\": \"2020-11-20 22:15:13\",\n" +
                "\t\"HKRQ\": \"2018-12-14 00:00:00\",\n" +
                "\t\"JKLXWY\": 0.0,\n" +
                "\t\"GUID\": \"b054e752-8d52-4236-b8e6-a2e1cfd2fa9a\",\n" +
                "\t\"SGXMB\": null,\n" +
                "\t\"JKJSSJ\": null,\n" +
                "\t\"XMJK.ID\": null,\n" +
                "\t\"HLXWY\": 0.0,\n" +
                "\t\"Code\": \"GXJGSANJ-XMHK20201120221552805\",\n" +
                "\t\"last_update_time\": \"2020-11-24 15:36:38\",\n" +
                "\t\"JKDW\": \"直管南宁片南宁海关项目\",\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 1138,\n" +
                "\t\"JKQSSJ\": null,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"JKYT\": null,\n" +
                "\t\"JKYEWY\": 0.0,\n" +
                "\t\"CreatorName\": \"李琪琪\",\n" +
                "\t\"HKJEWY\": 180.0,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"ModuleName\": \"项目还款\",\n" +
                "\t\"Dept.DeptId\": 1138,\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"YCXFYWY\": 0.0,\n" +
                "\t\"DeptName\": \"南宁海关技术业务用房\",\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"南宁海关技术业务用房\",\n" +
                "\t\"HasAttach\": false,\n" +
                "\t\"HBJWY\": 180.0,\n" +
                "\t\"CreateTime\": \"2020-11-20 22:15:13\",\n" +
                "\t\"PID\": 0,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1000064,\n" +
                "\t\"RZLL\": 0.0,\n" +
                "\t\"SGXM\": \"南宁海关项目\",\n" +
                "\t\"Effective\": true,\n" +
                "\t\"RZLX\": null,\n" +
                "\t\"ModuleCode\": \"GXJG.XMHKMK.XMHKModule\",\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("project_repayment", "项目还款", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("project_repayment", "项目还款", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("fn_project_repayment", "项目还款", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("fn_project_repayment", dwdFilePath, "ods_geps_meta_project_repayment_d_a", "");
        }

        // dws 建模 及 spark sql
        if (dwsFilePath != null && !"".equals(dwsFilePath)) {
            dictBaseDataUtil.dwsCreateDDL("fn_project_repayment_sum_d", "项目还款汇总表", dwsFilePath);
        }
    }
}
