package com.ccstay.ccstore.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartVO {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private String image;
    private String title;
    private Long price;// 添加购物车时的价格
    private Long realPrice;// 商品表真实价格
    private Integer num;


}
