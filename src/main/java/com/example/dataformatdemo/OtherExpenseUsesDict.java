package com.example.dataformatdemo;

import java.io.File;

public class OtherExpenseUsesDict {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        String dimFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "用途字典.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("other_expense_uses_dict", "其他支出用途字典");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"FQ\": null,\n" +
                "\t\"Code\": \"LSHY\",\n" +
                "\t\"JianPin\": \"LSHY\",\n" +
                "\t\"YTLX\": null,\n" +
                "\t\"Name\": \"临设耗用\",\n" +
                "\t\"Remark\": null,\n" +
                "\t\"master_bill_id\": 100004,\n" +
                "\t\"LastUpdateUser\": null,\n" +
                "\t\"last_update_time\": \"2022/4/1 19:45:11\",\n" +
                "\t\"LastUpdateTime\": null,\n" +
                "\t\"org_id\": 1,\n" +
                "\t\"Dept.DeptId\": 1,\n" +
                "\t\"PinYin\": \"linshehaoyong\",\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"Category.ID\": 100001,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"ID\": 100004,\n" +
                "\t\"id\": 100004,\n" +
                "\t\"data_status\": null,\n" +
                "\t\"org_name\": \"广西建工集团\"\n" +
                "}";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("other_expense_uses_dict", "其他支出用途字典", jsonString);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        dictBaseDataUtil.dimCreateDDL("other_expense_uses_dict", "其他支出用途字典", dimFilePath, jsonString);

    }
}
