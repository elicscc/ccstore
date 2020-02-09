package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItem extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -8879247924788259070L;
    private Integer id;
    private Integer oid;
    private Integer pid;
    private Integer num;
    private Long price;
    private String image;
    private String title;


}
