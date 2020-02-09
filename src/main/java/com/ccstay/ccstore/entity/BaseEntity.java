package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的父类
 * 
 * @author ta
 * @since 1.0
 */
@Setter
@Getter
abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	

	
	
}
