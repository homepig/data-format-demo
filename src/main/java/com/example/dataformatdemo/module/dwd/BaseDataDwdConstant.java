package com.example.dataformatdemo.module.dwd;

public class BaseDataDwdConstant {
    /**
     * dwd 表名
     */
    public static final String DWD_TABLE_NAME = "dwd_$ITEM$";

    /**
     * 核算对象sql
     */
    public static final String hsdx_sql = "account AS (select * from ods.ods_geps_meta_accounting_object_d_a where pt='\\${part_day}'),\n";

    /**
     * 合作单位
     */
    public static final String hzdw_sql = "unit AS (select * from dim.dim_cooperation_unit where pt='\\${part_day}'),\n";

    /**
     * 项目基本信息
     */
    public static final String xmjbxx_sql = "project AS (select * from ods.ods_geps_meta_project_info_d_a where pt='\\${part_day}'),\n";

    /**
     * 合同施工登记
     */
    public static final String sghtdj_sql = "contractbuild AS (select * from ods.ods_geps_meta_build_contract_d_a where pt='\\${part_day}'),\n";
}
