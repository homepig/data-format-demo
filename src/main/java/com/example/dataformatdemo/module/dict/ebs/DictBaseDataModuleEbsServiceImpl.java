package com.example.dataformatdemo.module.dict.ebs;

import org.springframework.stereotype.Service;

@Service
public class DictBaseDataModuleEbsServiceImpl implements DictBaseDataModuleEbsService {

    @Override
    public String getEbsDITableName(String item) {
        String ebs_d_i_table_name = DictEbsConstant.EBS_DICT_D_I.replaceAll("\\$ITEM\\$", item);
        return ebs_d_i_table_name;
    }

    @Override
    public String getEbsDATableName(String item) {
        String ebs_d_a_table_name = DictEbsConstant.EBS_DICT_DELETE_D_A.replaceAll("\\$ITEM\\$", item);
        return ebs_d_a_table_name;
    }

    @Override
    public String[] getEbsCreateTableSql(String item, String tableComment, boolean createDeleteTable) {
        String[] ss = new String[2];
        String ebs_d_i_table_name = this.getEbsDITableName(item);
        String create_ebs_d_i_table = DictEbsSql.ebs_dict_d_i.replaceAll("\\$TABLE_NAME\\$", ebs_d_i_table_name).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>ebs增量表<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_ebs_d_i_table);
        System.out.println("\r\n");
        ss[0] = create_ebs_d_i_table;

        String ebs_d_a_table_name = this.getEbsDATableName(item);
        String create_ebs_delete_d_a_table = DictEbsSql.ebs_dict_delete_d_a.replaceAll("\\$TABLE_NAME\\$", ebs_d_a_table_name).replaceAll("\\$TABLE_COMMENT\\$", tableComment);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>ebs删除表<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(create_ebs_delete_d_a_table);
        System.out.println("\r\n");

        ss[1] = create_ebs_delete_d_a_table;
        return ss;
    }
}
