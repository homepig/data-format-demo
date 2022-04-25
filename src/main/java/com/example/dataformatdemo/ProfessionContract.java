package com.example.dataformatdemo;

import java.io.File;

/**
 * 专业分包合同登记
 */
public class ProfessionContract {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "专业分包合同登记ods.xls";

        // dwd层
        String dwdFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "专业分包合同登记dwd.xls";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("profession_contract", "专业分包合同登记");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"ZZJDX\": \"零元整整\",\n" +
                "\t\"SGFW\": \"设计施工图及相关技术资料上消防工程分部分项的全部内容。\",\n" +
                "\t\"IsPost\": false,\n" +
                "\t\"ZLMBIDS\": null,\n" +
                "\t\"TBZT\": \"未同步\",\n" +
                "\t\"Remark\": \"消防工程专业分包\",\n" +
                "\t\"YQHTZJE\": 19324000.0,\n" +
                "\t\"IsMatchTaxRule\": false,\n" +
                "\t\"AFExt2\": null,\n" +
                "\t\"AFExt3\": null,\n" +
                "\t\"FBLB\": null,\n" +
                "\t\"AFExt1\": null,\n" +
                "\t\"ZYFBBJD.ID\": null,\n" +
                "\t\"ZJE\": 0.0,\n" +
                "\t\"JCBJE\": 0.0,\n" +
                "\t\"SourceObjKey\": null,\n" +
                "\t\"ZLMBs\": null,\n" +
                "\t\"JBR\": null,\n" +
                "\t\"ID\": 1357867,\n" +
                "\t\"id\": 1357867,\n" +
                "\t\"BizDate\": \"2022-01-08 00:00:00\",\n" +
                "\t\"KHYX\": null,\n" +
                "\t\"ZCHTYQL_MBZRCB\": 83.21,\n" +
                "\t\"CBMJ\": 0.0,\n" +
                "\t\"ZBJ\": 0.0,\n" +
                "\t\"JSFLFS\": \"基于含税算无税\",\n" +
                "\t\"HTXS\": null,\n" +
                "\t\"WGRQ\": \"2022-01-08 00:00:00\",\n" +
                "\t\"WBHL\": 0.0,\n" +
                "\t\"YQHTZJE_OLD\": 19324000.0,\n" +
                "\t\"SRFlag\": false,\n" +
                "\t\"Code\": \"GXJGJC-C1231022021003-ZYFBHTDJ-202201-0003\",\n" +
                "\t\"ZCZJ\": \"0\",\n" +
                "\t\"CGXM.DeptId\": 58510,\n" +
                "\t\"last_update_time\": \"2022-04-14 02:44:39\",\n" +
                "\t\"SGHTJE\": 150000000.0,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"JF.ID\": 1006454,\n" +
                "\t\"ZYRYSL\": 0,\n" +
                "\t\"HTFKBL\": 0.0,\n" +
                "\t\"SFWD\": false,\n" +
                "\t\"IsRefeneced\": null,\n" +
                "\t\"YQHTZJE_MBZRCB\": 19324000.0,\n" +
                "\t\"PushToLaborState\": null,\n" +
                "\t\"WBBZ\": null,\n" +
                "\t\"YZM\": null,\n" +
                "\t\"JGCS\": 0,\n" +
                "\t\"HTJESFXG\": false,\n" +
                "\t\"SE\": 0.0,\n" +
                "\t\"IsBack\": false,\n" +
                "\t\"WJLX_ModuleCode\": \"GEPS.Professional.Contract.ZYFBHTDJModule\",\n" +
                "\t\"ContractHTTKZYs\": null,\n" +
                "\t\"TSDJC\": \"未同步\",\n" +
                "\t\"Attachs\": null,\n" +
                "\t\"SourceSysKey\": null,\n" +
                "\t\"Deleted\": false,\n" +
                "\t\"AQSGXKZH\": null,\n" +
                "\t\"FBHTDJCDYSRHTDJBHYJ\": false,\n" +
                "\t\"org_name\": \"贵港市荷润物流城(一期)（1#快递用房、2#快递用房）\",\n" +
                "\t\"YQHTZJE_MBZRCB_OLD\": 19324000.0,\n" +
                "\t\"QTFYs\": null,\n" +
                "\t\"TBSJ\": null,\n" +
                "\t\"HTZT\": \"签订\",\n" +
                "\t\"IsSupplement\": false,\n" +
                "\t\"CreateTime\": \"2022-01-08 10:15:14\",\n" +
                "\t\"SKDW_ID\": 0,\n" +
                "\t\"KHZH\": null,\n" +
                "\t\"FBS.ID\": 1390522,\n" +
                "\t\"DJTBZT2\": \"未同步\",\n" +
                "\t\"ZBGYS\": null,\n" +
                "\t\"PID\": 0,\n" +
                "\t\"YZZD.ID\": null,\n" +
                "\t\"JianPin\": \"HRWLCXMXFZYSGFBHT\",\n" +
                "\t\"DJTBZT1\": \"未同步\",\n" +
                "\t\"HTZZ\": null,\n" +
                "\t\"WBHTZZJ\": 0.0,\n" +
                "\t\"Effective\": true,\n" +
                "\t\"KQHTZJE_FL\": 2994698.52,\n" +
                "\t\"FKFS\": null,\n" +
                "\t\"YYZT\": \"未用印\",\n" +
                "\t\"KQZCHTZJE\": 118697378.29,\n" +
                "\t\"ModuleCode\": \"GEPS.Professional.Contract.ZYFBHTDJModule\",\n" +
                "\t\"ZCHTYQL\": 20.0,\n" +
                "\t\"YQHTZJE_FL\": 19324000.0,\n" +
                "\t\"YQZCHTZJE_OLD\": 31302621.71,\n" +
                "\t\"IsPostError\": false,\n" +
                "\t\"ShareState\": \"未共享\",\n" +
                "\t\"YQZCHTZJE\": 31302621.71,\n" +
                "\t\"YQHTZJE_FL_OLD\": 19324000.0,\n" +
                "\t\"HTYQL_MBZRCB\": 51.37,\n" +
                "\t\"JZBZ\": false,\n" +
                "\t\"BKDK\": false,\n" +
                "\t\"Name\": \"荷润物流城项目【消防】--专业施工分包合同\",\n" +
                "\t\"master_bill_id\": 1357867,\n" +
                "\t\"XMJBXX.ID\": null,\n" +
                "\t\"JSLX.ID\": null,\n" +
                "\t\"LastUpdateTime\": \"2022-04-14 02:44:39\",\n" +
                "\t\"DYYHT\": null,\n" +
                "\t\"YF.ID\": 1390522,\n" +
                "\t\"data_status\": true,\n" +
                "\t\"TaxRuleInValidFlag\": null,\n" +
                "\t\"YZURL\": null,\n" +
                "\t\"HTTKZY\": null,\n" +
                "\t\"HTFL.ID\": 200013,\n" +
                "\t\"DYYHTJE\": 0.0,\n" +
                "\t\"GUID\": \"20ff959c-ac5a-4f36-a213-dde8151b1ada\",\n" +
                "\t\"MBZRCBJE\": 37619264.68,\n" +
                "\t\"YQZCHTZJE_MBZRCB\": 31302621.71,\n" +
                "\t\"HTYQL\": 12.0,\n" +
                "\t\"State\": \"审批通过\",\n" +
                "\t\"org_id\": 58510,\n" +
                "\t\"WJLX\": \"专业分包合同\",\n" +
                "\t\"PushStateInt\": 0,\n" +
                "\t\"KGRQ\": \"2022-01-08 00:00:00\",\n" +
                "\t\"CreatorName\": \"谭新凡\",\n" +
                "\t\"YYR.UserId\": null,\n" +
                "\t\"PrintTimes\": 0,\n" +
                "\t\"Creator.UserId\": null,\n" +
                "\t\"TempSaved\": false,\n" +
                "\t\"YYFS\": 1,\n" +
                "\t\"ModuleName\": \"专业分包合同登记\",\n" +
                "\t\"SKDW\": null,\n" +
                "\t\"IsNotOpen\": false,\n" +
                "\t\"ZXZZ\": null,\n" +
                "\t\"JCZBGYS.Id\": null,\n" +
                "\t\"TaxRatio\": 0.0,\n" +
                "\t\"GZNR\": \"设计施工图及相关技术资料上消防工程分部分项的全部内容。\",\n" +
                "\t\"PinYin\": \"herunwuliuchengxiangmuxiaofangzhuanyeshigongfenbaohetong\",\n" +
                "\t\"Dept.DeptId\": 58510,\n" +
                "\t\"DYSRHTJE\": 0.0,\n" +
                "\t\"BABH\": null,\n" +
                "\t\"DeptName\": \"贵港市荷润物流城(一期)（1#快递用房、2#快递用房）\",\n" +
                "\t\"FBSL.ID\": null,\n" +
                "\t\"ZLMB.ID\": null,\n" +
                "\t\"HasAttach\": true,\n" +
                "\t\"JSLBNCHTL\": false,\n" +
                "\t\"MBZRCBJE_FL\": 22318698.52,\n" +
                "\t\"JCTBSJ\": null,\n" +
                "\t\"HTYQL_FL\": 86.58,\n" +
                "\t\"YYZZH\": null,\n" +
                "\t\"KQZCHTZJE_MBZRCB\": 6316642.97,\n" +
                "\t\"GQ\": 1,\n" +
                "\t\"DJTBSJ2\": null,\n" +
                "\t\"DJTBSJ1\": null,\n" +
                "\t\"JCZBTask.Id\": null,\n" +
                "\t\"LastUpdateUserName\": null,\n" +
                "\t\"RID\": 1357867,\n" +
                "\t\"FBHTYSMXs\": null,\n" +
                "\t\"HZJE\": 0.0,\n" +
                "\t\"DYZD.ID\": 100379,\n" +
                "\t\"ContractHTZFJHs\": null,\n" +
                "\t\"JSWB\": false,\n" +
                "\t\"GDZT\": \"未归档\",\n" +
                "\t\"FKSQCHTYFBHXS\": true,\n" +
                "\t\"PBSID\": 0,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"YQZCHTZJE_MBZRCB_OLD\": 31302621.71,\n" +
                "\t\"NSRLB.ID\": null\n" +
                "}";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("profession_contract", "专业分包合同登记", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("profession_contract", "专业分包合同登记", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("prof_profession_contract", "专业分包合同登记", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("prof_profession_contract", dwdFilePath, "ods_geps_meta_profession_contract_d_a", "");
        }
    }
}
