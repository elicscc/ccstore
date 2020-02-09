package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 地址 省市区列表
 *
 * @author Alex
 */
@Getter
@Setter
public class District {
    private Integer id;
    private String parent;
    private String code;
    private String name;


}
