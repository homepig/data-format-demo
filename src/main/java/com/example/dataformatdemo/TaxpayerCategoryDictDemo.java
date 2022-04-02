package com.example.dataformatdemo;

public class TaxpayerCategoryDictDemo {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        String dimFilePath = "";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("", "");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("", "", jsonString);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        dictBaseDataUtil.dimCreateDDL("", "", dimFilePath, jsonString);
    }
}
