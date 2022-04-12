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
    public static final String sghtdj_sql = "contractbuild AS (select * from ods.ods_geps_meta_construction_contract_d_a where pt='\\${part_day}'),\n";

    /**
     * 资金支付
     */
    public static final String zjzf_sql = "paytype AS (select * from dim.dim_fund_pay_type_dict where pt='\\${part_day}'),\n";

    /**
     * 项目借款
     */
    public static final String xmjk_sql = "borrowmoney AS (select * from ods.ods_geps_meta_project_borrow_money_d_a where pt='\\${part_day}'),\n";

    /**
     * 销项发票分类
     */
    public static final String xxfpfl_sql = "xxfpfl AS (select * from dim.dim_output_invoice_category where pt='\\${part_day}'),\n";

    /**
     * 合同基本信息表
     */
    public static final String htjbxxb_sql = "htjbxxb AS (select * from ods.ods_oa_wv_contract_d_a where pt='\\${part_day}'),\n";

    /**
     * 减税性质字典
     */
    public static final String jsxzdict_sql = "jsxzdict AS (select * from dim.dim_tax_cuts_nature_dict where pt='\\${part_day}'),";

    /**
     * 应税项目字典
     */
    public static final String ysxmdict_sql = "ysxmdict AS (select * from dim.dim_taxable_pro_code_dict where pt='\\${part_day}'),";


}
