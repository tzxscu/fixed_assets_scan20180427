package com.tzxscu.cdfy.beans;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/20.
 */

public class exp_dict_bean {
    private String exp_name;
    private String exp_code;
    private String exp_spec;
    private String firm_id;
    private String unit;
    private String location;
    private String use_dept;
    private String import_date;
    private  int use_flag;


    public int getUse_flag() {
        return use_flag;
    }

    public void setUse_flag(int use_flag) {
        this.use_flag = use_flag;
    }

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public String getExp_code() {
        return exp_code;
    }

    public void setExp_code(String exp_code) {
        this.exp_code = exp_code;
    }

    public String getExp_spec() {
        return exp_spec;
    }

    public void setExp_spec(String exp_spec) {
        this.exp_spec = exp_spec;
    }

    public String getFirm_id() {
        return firm_id;
    }

    public void setFirm_id(String firm_id) {
        this.firm_id = firm_id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUse_dept() {
        return use_dept;
    }

    public void setUse_dept(String use_dept) {
        this.use_dept = use_dept;
    }

    public String getImport_date() {
        return import_date;
    }

    public void setImport_date(String import_date) {
        this.import_date = import_date;
    }
}
