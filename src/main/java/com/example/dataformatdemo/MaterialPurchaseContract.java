package com.example.dataformatdemo;

import java.io.File;

/**
 * 材料采购合同
 */
public class MaterialPurchaseContract {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "材料采购合同ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "材料采购合同dwd.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("material_purchase_contract", "材料采购合同");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ZZJDX\": \"壹佰壹拾叁万元整\",\n" +
                "\t\"IsPost\": false,\n" +
                "\t\"TBZT\": null,\n" +
                "\t\"Remark\": null,\n" +
                "\t\"YQHTZJE\": 1130000.0,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"ZJE\": 1130000.0,\n" +
                "\t\"JCBJE\": 1097087.37,\n" +
                "\t\"SourceObjKey\": null,\n" +
                "\t\"JBR\": null,\n" +
                "\t\"ID\": 1371033,\n" +
                "\t\"id\": 1371033,\n" +
                "\t\"JSEBNCHTE\": true,\n" +
                "\t\"BizDate\": \"2021-12-21 00:00:00\",\n" +
                "\t\"KHYX\": null,\n" +
                "\t\"ZCHTYQL_MBZRCB\": 0.0,\n" +
                "\t\"HTXS\": null,\n" +
                "\t\"WBHL\": 0.0,\n" +
                "\t\"YQHTZJE_OLD\": 0.0,\n" +
                "\t\"Code\": \"GXJGQJ-003-CLCGHT-202112-0099\",\n" +
                "\t\"CGXM.DeptId\": 39894,\n" +
                "\t\"last_update_time\": \"2022-04-13 02:04:16\",\n" +
                "\t\"SGHTJE\": 0.0,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"JF.ID\": 1053707,\n" +
                "\t\"CLCGBJD.ID\": null,\n" +
                "\t\"HTSLCMBZRCBSLBYXBC\": false,\n" +
                "\t\"SFWD\": false,\n" +
                "\t\"HTFKBL\": 0.0,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"YQHTZJE_MBZRCB\": 1130000.0,\n" +
                "\t\"ZBTask.Id\": null,\n" +
                "\t\"WBBZ\": null,\n" +
                "\t\"YZM\": null,\n" +
                "\t\"JGCS\": 0,\n" +
                "\t\"WLTBSJ\": null,\n" +
                "\t\"SE\": 32912.63,\n" +
                "\t\"IsBack\": false,\n" +
                "\t\"WJLX_ModuleCode\": \"GEPS.Material.Purchase.CLCGHTModule\",\n" +
                "\t\"ContractHTTKZYs\": null,\n" +
                "\t\"TSDJC\": \"未同步\",\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"CLLBJEGKs\": null,\n" +
                "\t\"SourceSysKey\": null,\n" +
                "\t\"CMBZRCBCLLB\": null,\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"org_name\": \"重庆城际空间站三号地块一标段施工总承包工程\",\n" +
                "\t\"YQHTZJE_MBZRCB_OLD\": 0.0,\n" +
                "\t\"QTFYs\": null,\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"GYSGYCLLB\": null,\n" +
                "\t\"HTZT\": \"签订\",\n" +
                "\t\"IsSupplement\": false,\n" +
                "\t\"CreateTime\": \"2021-12-21 15:07:19\",\n" +
                "\t\"SKDW_ID\": 0,\n" +
                "\t\"KHZH\": null,\n" +
                "\t\"ZBGYS.Id\": null,\n" +
                "\t\"DJTBZT2\": \"未同步\",\n" +
                "\t\"PID\": 1308782,\n" +
                "\t\"YZZD.ID\": null,\n" +
                "\t\"JSDJBNCHTDJ\": false,\n" +
                "\t\"ZBGYSMC\": null,\n" +
                "\t\"JianPin\": \"SSCGHTBCXY\",\n" +
                "\t\"DJTBZT1\": \"未同步\",\n" +
                "\t\"HTZZ\": null,\n" +
                "\t\"WBHTZZJ\": 0.0,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"KQHTZJE_FL\": -1130000.0,\n" +
                "\t\"FKFS\": null,\n" +
                "\t\"YYZT\": \"未用印\",\n" +
                "\t\"KQZCHTZJE\": -1130000.0,\n" +
                "\t\"ModuleCode\": \"GEPS.Material.Purchase.CLCGHTModule\",\n" +
                "\t\"LJRKLBNCHTL\": false,\n" +
                "\t\"RKDJBNCHTDJ\": false,\n" +
                "\t\"ZCHTYQL\": 0.0,\n" +
                "\t\"HTDJCMBZRCBDJBHYJ\": false,\n" +
                "\t\"YQHTZJE_FL\": 1130000.0,\n" +
                "\t\"YQZCHTZJE_OLD\": 0.0,\n" +
                "\t\"IsPostError\": false,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"YQZCHTZJE\": 1130000.0,\n" +
                "\t\"YQHTZJE_FL_OLD\": 0.0,\n" +
                "\t\"LRL\": 0.0,\n" +
                "\t\"HTYQL_MBZRCB\": 0.0,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"BKDK\": false,\n" +
                "\t\"Name\": \"碎石采购合同补充协议\",\n" +
                "\t\"master_bill_id\": 1371033,\n" +
                "\t\"XMJBXX.ID\": null,\n" +
                "\t\"JSLX.ID\": null,\n" +
                "\t\"LastUpdateTime\": \"2022-04-13 02:04:16\",\n" +
                "\t\"CLCGHTCLMXs\": null,\n" +
                "\t\"DYYHT\": \"碎石采购合同\",\n" +
                "\t\"YF.ID\": 1352204,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"TaxRuleInValidFlag\": null,\n" +
                "\t\"YZURL\": null,\n" +
                "\t\"HTSLCMBZRCBSLBHYJ\": false,\n" +
                "\t\"HTTKZY\": null,\n" +
                "\t\"HTFL.ID\": 530001,\n" +
                "\t\"DYYHTJE\": 90000.0,\n" +
                "\t\"GUID\": \"946143ce-54f5-44ae-a1f4-f590a07e301e\",\n" +
                "\t\"MBZRCBJE\": 0.0,\n" +
                "\t\"CLZDJ.ID\": null,\n" +
                "\t\"YQZCHTZJE_MBZRCB\": 1130000.0,\n" +
                "\t\"HTYQL\": 0.0,\n" +
                "\t\"WLTBZT\": \"未同步\",\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 39894,\n" +
                "\t\"WJLX\": \"材料采购合同\",\n" +
                "\t\"PushStateInt\": 0,\n" +
                "\t\"CreatorName\": \"谢俊峰\",\n" +
                "\t\"YYR.UserId\": null,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"YYFS\": 1,\n" +
                "\t\"ModuleName\": \"材料采购合同\",\n" +
                "\t\"SKDW\": null,\n" +
                "\t\"HTDJCMBZRCBDJBYXBC\": false,\n" +
                "\t\"IsNotOpen\": false,\n" +
                "\t\"TaxRatio\": 0.0,\n" +
                "\t\"GZNR\": null,\n" +
                "\t\"PinYin\": \"suishicaigouhetongbuchongxieyi\",\n" +
                "\t\"Dept.DeptId\": 39894,\n" +
                "\t\"BABH\": null,\n" +
                "\t\"DeptName\": \"重庆城际空间站三号地块一标段施工总承包工程\",\n" +
                "\t\"TSZT\": null,\n" +
                "\t\"HasAttach\": true,\n" +
                "\t\"MBZRCBJE_FL\": 0.0,\n" +
                "\t\"JCTBSJ\": null,\n" +
                "\t\"HTYQL_FL\": 0.0,\n" +
                "\t\"KQZCHTZJE_MBZRCB\": -1130000.0,\n" +
                "\t\"DJTBSJ2\": null,\n" +
                "\t\"DJTBSJ1\": null,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1308782,\n" +
                "\t\"HZJE\": 0.0,\n" +
                "\t\"DYZD.ID\": 100109,\n" +
                "\t\"ContractHTZFJHs\": null,\n" +
                "\t\"CLLB.ID\": 1000001,\n" +
                "\t\"JSWB\": false,\n" +
                "\t\"GDZT\": \"未归档\",\n" +
                "\t\"FKSQCHTYFBHXS\": true,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"YQZCHTZJE_MBZRCB_OLD\": 0.0,\n" +
                "\t\"NSRLB.ID\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("material_purchase_contract", "材料采购合同", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("material_purchase_contract", "材料采购合同", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("gm_material_purchase_contract", "材料采购合同", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("gm_material_purchase_contract", dwdFilePath, "ods_geps_meta_material_purchase_contract_d_a", "");
        }
    }
}
