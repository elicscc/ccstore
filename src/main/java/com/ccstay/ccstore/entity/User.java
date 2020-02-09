package com.ccstay.ccstore.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户数据的实体类
 */
// @JsonInclude(value=Include.NON_NULL)
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    private static final long serialVersionUID = -1197587481888673428L;
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer isDelete;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;


}
