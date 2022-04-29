package com.example.dataformatdemo;

import java.io.File;

/**
 * 进行发票
 */
public class InputInvoice {
    public static void main(String[] args) {
        DictBaseDataUtil dictBaseDataUtil = DictBaseDataUtil.getInstance();

        // 维度表 excel
        String dimFilePath = "";

        // ods层 增量表和全量表
        String odsFilePath = "";

        // dwd层
        String dwdFilePath = "";

        // dws层
        String dwsFilePath = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "进项发票ads.xls";

        //1. ebs 建模
//        dictBaseDataUtil.createEbsTables("uses_dict", "用途字典");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "";

        //3. ods 建模 以及开发
        if (odsFilePath != null && !"".equals(odsFilePath)) {
            dictBaseDataUtil.odsCreateDDL("", "", jsonString, odsFilePath);
        } else {
//            dictBaseDataUtil.odsCreateDDL("", "", jsonString);
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
            dictBaseDataUtil.dwsCreateDDL("pro_input_invoice_d", "进项发票", dwsFilePath);
        }
    }
}
