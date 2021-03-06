package com.example.dataformatdemo;

public class DictDemo {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "";

        // dwd层
        String dwdFilePath = "";

        // dws层
        String dwsFilePath = "";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("uses_dict", "用途字典");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("", "", jsonString, odsFilePath);
        } else {
            dictBaseDataUtil.odsCreateDDL("", "", jsonString);
        }
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        if (dimFilePath != null && !"".equals(dimFilePath)) {
            dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
        }

        // dwd 建模
        if (dwdFilePath != null && !"".equals(dwdFilePath)) {
            dictBaseDataUtil.dwdCreateDDL("", "", dwdFilePath);
            dictBaseDataUtil.dwdSparkSql("", dwdFilePath, "", "");
        }

        // dws 建模 及 spark sql
        if (dwsFilePath != null && !"".equals(dwsFilePath)) {
            dictBaseDataUtil.dwsCreateDDL("", "", dwsFilePath);
        }
    }
}
