package com.example.dataformatdemo;

import java.io.File;

public class ContractCategoryDemo {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        String dimFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "合同分类.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("contract_category", "合同分类");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"IsLeaf\": true,\n" +
                "\t\"OrderNo\": 1005,\n" +
                "\t\"Remark\": \"H541-Cxx-02,1001A21000000001017Z\",\n" +
                "\t\"Name\": \"其他收入合同\",\n" +
                "\t\"master_bill_id\": 200005,\n" +
                "\t\"LastUpdateUser\": null,\n" +
                "\t\"FullCode\": \"1001.1002.1005\",\n" +
                "\t\"LastUpdateTime\": null,\n" +
                "\t\"Dept.DeptId\": null,\n" +
                "\t\"PinYin\": \"qitashouruhetong\",\n" +
                "\t\"ID\": 200005,\n" +
                "\t\"id\": 200005,\n" +
                "\t\"data_status\": null,\n" +
                "\t\"org_name\": null,\n" +
                "\t\"XTBS\": \"动态扩展\",\n" +
                "\t\"PID\": 500002,\n" +
                "\t\"JianPin\": \"QTSRHT\",\n" +
                "\t\"Code\": \"1005\",\n" +
                "\t\"last_update_time\": \"2022/4/1 19:50:45\",\n" +
                "\t\"HTFLSX.ID\": 200002,\n" +
                "\t\"org_id\": null,\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"HTLX\": \"收入类\",\n" +
                "\t\"IsSystem\": true,\n" +
                "\t\"Level\": 3,\n" +
                "\t\"creator_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("contract_category", "合同分类", jsonString);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        dictBaseDataUtil.dimCreateDDL("contract_category", "合同分类", dimFilePath, jsonString);

    }
}
