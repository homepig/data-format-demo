package com.example.dataformatdemo;

import java.io.File;

public class TaxpayerCategoryDictDemo {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        String dimFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "纳税人类别字典.xlsx";

        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("taxpayer_category_dict", "纳税人类别字典");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"Code\": \"N002\",\n" +
                "\t\"JianPin\": \"XGMNSR\",\n" +
                "\t\"Name\": \"小规模纳税人\",\n" +
                "\t\"Remark\": null,\n" +
                "\t\"master_bill_id\": 400002,\n" +
                "\t\"LastUpdateUser\": null,\n" +
                "\t\"last_update_time\": \"2022/4/2 8:07:33\",\n" +
                "\t\"LastUpdateTime\": null,\n" +
                "\t\"org_id\": null,\n" +
                "\t\"Dept.DeptId\": null,\n" +
                "\t\"PinYin\": \"xiaoguimonashuiren\",\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"ID\": 400002,\n" +
                "\t\"id\": 400002,\n" +
                "\t\"data_status\": null,\n" +
                "\t\"org_name\": null\n" +
                "}";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("taxpayer_category_dict", "纳税人类别字典", jsonString);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序

        dictBaseDataUtil.dimCreateDDL("taxpayer_category_dict", "纳税人类别字典", dimFilePath, jsonString);
    }
}
