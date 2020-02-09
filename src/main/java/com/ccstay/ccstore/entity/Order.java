package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class Order extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 7707798537097211839L;
    private Integer id;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Integer status;
    private Long price;
    private Date orderTime;
    private Date payTime;


}
