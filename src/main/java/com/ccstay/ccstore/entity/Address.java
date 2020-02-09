package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 264451555437295387L;
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private Integer provinceCode;
    private String cityName;
    private Integer cityCode;
    private String areaName;
    private Integer areaCode;
    private Integer zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;



    
}
