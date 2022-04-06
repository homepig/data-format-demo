package com.example.dataformatdemo;

import com.example.dataformatdemo.module.dict.dim.DictBaseDataModuleDimService;
import com.example.dataformatdemo.module.dict.dim.DictBaseDataModuleDimServiceImpl;
import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsService;
import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsServiceImpl;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsService;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsServiceImpl;
import lombok.Data;

@Data
public class DictBaseDataUtil {

    private DictBaseDataModuleEbsService dictBaseDataModuleEbsService;

    private DictBaseDataModuleOdsService dictBaseDataModuleOdsService;

    private DictBaseDataModuleDimService dictBaseDataModuleDimService;

    /**
     * ebs建模
     */
    public void createEbsTables(String itemName, String itemDesc) {
        dictBaseDataModuleEbsService.getEbsCreateTableSql(itemName, itemDesc, true);
    }

    /**
     * ods建模语句
     *
     * @param itemName
     * @param itemDesc
     */
    public void odsCreateDDL(String itemName, String itemDesc, String jsonString) {
        dictBaseDataModuleOdsService.getObsDictDISql(itemName, itemDesc, jsonString);
        dictBaseDataModuleOdsService.getObsDictDeleteDASql(itemName, itemDesc);
        dictBaseDataModuleOdsService.getObsDictDASql(itemName, itemDesc, jsonString);
        dictBaseDataModuleOdsService.getObsDictDISparkSql(itemName, jsonString);
        dictBaseDataModuleOdsService.getObsDictDdSparkSql(itemName);
        dictBaseDataModuleOdsService.getObsDictDASparkSql(itemName, jsonString);
    }

    public void odsCreateDDL(String itemName, String itemDesc, String jsonString, String odsFilePath) {
        dictBaseDataModuleOdsService.getObsDictDISql(itemName, itemDesc, jsonString, odsFilePath);
        dictBaseDataModuleOdsService.getObsDictDeleteDASql(itemName, itemDesc);
        dictBaseDataModuleOdsService.getObsDictDASql(itemName, itemDesc, jsonString, odsFilePath);
        dictBaseDataModuleOdsService.getObsDictDISparkSql(itemName, jsonString);
        dictBaseDataModuleOdsService.getObsDictDdSparkSql(itemName);
        dictBaseDataModuleOdsService.getObsDictDASparkSql(itemName, jsonString);
    }

    public void dimCreateDDL(String itemName, String itemDesc, String dimFilePath, String jsonString) {
        dictBaseDataModuleDimService.getDimCreateTableSql(itemName, itemDesc, dimFilePath);
        dictBaseDataModuleDimService.getDimSparkSql(itemName, itemName, jsonString);
    }

    /**
     * 获取实例
     * @return
     */
    public static DictBaseDataUtil getInstance() {
        DictBaseDataModuleEbsService dictBaseDataModuleEbsService = new DictBaseDataModuleEbsServiceImpl();
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.setDictBaseDataModuleEbsService(dictBaseDataModuleEbsService);
        DictBaseDataModuleDimService dictBaseDataModuleDimService = new DictBaseDataModuleDimServiceImpl();
        dictBaseDataModuleDimService.setDictBaseDataModuleOdsService(dictBaseDataModuleOdsService);
        DictBaseDataUtil dictBaseDataUtil = new DictBaseDataUtil();
        dictBaseDataUtil.setDictBaseDataModuleEbsService(dictBaseDataModuleEbsService);
        dictBaseDataUtil.setDictBaseDataModuleOdsService(dictBaseDataModuleOdsService);
        dictBaseDataUtil.setDictBaseDataModuleDimService(dictBaseDataModuleDimService);
        return dictBaseDataUtil;
    }

    public static void main(String[] args) {
        // 在控制台寻找语句
        DictBaseDataModuleEbsService dictBaseDataModuleEbsService = new DictBaseDataModuleEbsServiceImpl();
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.setDictBaseDataModuleEbsService(dictBaseDataModuleEbsService);
        DictBaseDataModuleDimService dictBaseDataModuleDimService = new DictBaseDataModuleDimServiceImpl();
        dictBaseDataModuleDimService.setDictBaseDataModuleOdsService(dictBaseDataModuleOdsService);
        DictBaseDataUtil dictBaseDataUtil = new DictBaseDataUtil();
        dictBaseDataUtil.setDictBaseDataModuleEbsService(dictBaseDataModuleEbsService);
        dictBaseDataUtil.setDictBaseDataModuleOdsService(dictBaseDataModuleOdsService);
        dictBaseDataUtil.setDictBaseDataModuleDimService(dictBaseDataModuleDimService);
        //1. ebs 建模
        dictBaseDataUtil.createEbsTables("output_invoice_classify", "销项发票分类");
        //2. 获取 json结构的ebs数据模型
        String jsonString = "{\n" +
                "\t\"YWLX\": \"工程收入\",\n" +
                "\t\"Code\": \"01\",\n" +
                "\t\"JianPin\": \"GCSR\",\n" +
                "\t\"Name\": \"工程收入\",\n" +
                "\t\"Remark\": null,\n" +
                "\t\"master_bill_id\": 400001,\n" +
                "\t\"LastUpdateUser\": null,\n" +
                "\t\"last_update_time\": \"2022/4/1 11:02:45\",\n" +
                "\t\"LastUpdateTime\": null,\n" +
                "\t\"org_id\": 1,\n" +
                "\t\"Dept.DeptId\": 1,\n" +
                "\t\"PinYin\": \"gongchengshouru\",\n" +
                "\t\"creator_id\": null,\n" +
                "\t\"creator_name\": null,\n" +
                "\t\"ID\": 400001,\n" +
                "\t\"id\": 400001,\n" +
                "\t\"data_status\": null,\n" +
                "\t\"org_name\": \"广西建工集团\"\n" +
                "}";

        //3. ods 建模 以及开发
        dictBaseDataUtil.odsCreateDDL("output_invoice_classify", "销项发票分类", jsonString);
        //4. 字段注释

        //5. dim 建模 以及开发             AAAAAAAAAAAAAAAAA注：需核对select字段的数量及顺序
    }
}
