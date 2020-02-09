package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 购物车实体类
 * 
 * @author Alex
 *
 */
@Setter
@Getter
public class Cart extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3956670892850293711L;
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String created_user;
    private Date created_time;
    private String modified_user;
    private Date modified_time;

}
