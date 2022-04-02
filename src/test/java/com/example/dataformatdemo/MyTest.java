package com.example.dataformatdemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSONObject;
import com.example.dataformatdemo.module.dict.dim.DictBaseDataModuleDimService;
import com.example.dataformatdemo.module.dict.dim.DictBaseDataModuleDimServiceImpl;
import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsService;
import com.example.dataformatdemo.module.dict.ebs.DictBaseDataModuleEbsServiceImpl;
import com.example.dataformatdemo.module.dict.ebs.DictEbsSql;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsService;
import com.example.dataformatdemo.module.dict.ods.DictBaseDataModuleOdsServiceImpl;
import com.example.dataformatdemo.module.dict.util.ExcelData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MyTest {
    @Test
    public void m1() {
        String ebs_d_i_table_name = "ebs_geps_meta_engineering_category_dict_d_i";
        String ebs_d_i_table_comment = "工程类别增量";

        String create_ebs_d_i_table = DictEbsSql.ebs_dict_d_i.replaceAll("\\$TABLE_NAME\\$", ebs_d_i_table_name).replaceAll("\\$TABLE_COMMENT\\$", ebs_d_i_table_comment);
        System.out.println(create_ebs_d_i_table);

        String ebs_d_a_table_name = "ebs_geps_meta_engineering_category_dict_delete_d_a";
        String ebs_d_a_table_comment = "工程类别删除";

        String create_ebs_delete_d_a_table = DictEbsSql.ebs_dict_delete_d_a.replaceAll("\\$TABLE_NAME\\$", ebs_d_a_table_name).replaceAll("\\$TABLE_COMMENT\\$", ebs_d_a_table_comment);
        System.out.println(create_ebs_delete_d_a_table);

        String ebs_delete_d_a_table_name = "";
        System.out.println("结束");
    }

    @Test
    public void m2() {
        DictBaseDataModuleEbsService dictBaseDataModuleService = new DictBaseDataModuleEbsServiceImpl();
        String[] ss = dictBaseDataModuleService.getEbsCreateTableSql("output_invoice_classify", "销项发票分类", true);
        System.out.println(ss);
    }

    @Test
    public void m3() {
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        String sqlField = dictBaseDataModuleOdsService.getObsDictDISql("output_invoice_classify", "销项发票分类", "{\n" +
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
                "}");
    }

    @Test
    public void m4() {
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.getObsDictDeleteDASql("output_invoice_classify", "销项发票分类");
    }

    @Test
    public void m5() {
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.getObsDictDASql("output_invoice_classify", "销项发票分类", "{\n" +
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
                "}");
    }

    @Test
    public void m6() {
        DictBaseDataModuleEbsService dictBaseDataModuleService = new DictBaseDataModuleEbsServiceImpl();
        DictBaseDataModuleOdsServiceImpl dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.setDictBaseDataModuleEbsService(dictBaseDataModuleService);
        dictBaseDataModuleOdsService.getObsDictDISparkSql("output_invoice_classify",  "{\n" +
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
                "}");
    }

    @Test
    public void m7() {
        DictBaseDataModuleEbsService dictBaseDataModuleService = new DictBaseDataModuleEbsServiceImpl();
        DictBaseDataModuleOdsServiceImpl dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.setDictBaseDataModuleEbsService(dictBaseDataModuleService);
        dictBaseDataModuleOdsService.getObsDictDdSparkSql("output_invoice_classify");
    }

    @Test
    public void m8() {
        DictBaseDataModuleEbsService dictBaseDataModuleService = new DictBaseDataModuleEbsServiceImpl();
        DictBaseDataModuleOdsServiceImpl dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        dictBaseDataModuleOdsService.setDictBaseDataModuleEbsService(dictBaseDataModuleService);
        dictBaseDataModuleOdsService.getObsDictDASparkSql("output_invoice_classify", "{\n" +
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
                "}");
    }

    @Test
    public void m9() {
        String fileName = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "ods建模模板文件.xlsx";
        Map<String, String> fields = new LinkedHashMap<>();
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(datalist -> {
            for (ExcelData excelData : datalist) {
                System.out.println(excelData.getDesc() + ":" + excelData.getCode());
                fields.put(excelData.getCode(), excelData.getDesc());
            }
        })).sheet().doRead();
    }

    @Test
    public void m10() {
        String fileName = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "ods建模模板文件.xlsx";
        DictBaseDataModuleDimService dictBaseDataModuleDimService = new DictBaseDataModuleDimServiceImpl();
        dictBaseDataModuleDimService.getDimCreateTableSql("output_invoice_classify", "销项发票分类", fileName);
    }

    @Test
    public void m11() {
        String fileName = "E:\\data-format-demo\\src\\main\\resources" + File.separator + "ods建模模板文件.xlsx";
        DictBaseDataModuleOdsService dictBaseDataModuleOdsService = new DictBaseDataModuleOdsServiceImpl();
        DictBaseDataModuleDimService dictBaseDataModuleDimService = new DictBaseDataModuleDimServiceImpl();
        dictBaseDataModuleDimService.setDictBaseDataModuleOdsService(dictBaseDataModuleOdsService);
        dictBaseDataModuleDimService.getDimSparkSql("output_invoice_classify", "output_invoice_classify", "{\n" +
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
                "}");
    }
}
