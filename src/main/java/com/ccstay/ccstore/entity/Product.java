package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseEntity {
	/**
     * 
     */
    private static final long serialVersionUID = 5684071538475539682L;
    private Integer id;
	private Integer categoryId;
	private String itemType;
	private String title;
	private String sellPoint;
	private Long price;
	private Integer num;
	private String image;
	private Integer status;
	private Integer priority;



}
