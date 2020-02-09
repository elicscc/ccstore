package com.ccstay.ccstore.service;

import com.ccstay.ccstore.controller.ex.ProductNotFoundException;
import com.ccstay.ccstore.entity.Product;

import java.util.List;


/**
 * 热销商品查询
 * 
 * @author Alex 检索前四条
 *
 */
public interface IProductService {
    List<Product> getHotList();

    List<Product> getNewList();

    Product findById(Integer id) throws ProductNotFoundException;
    
    void reduceNum(Integer pid, Integer num);
    
    void addNum(Integer pid, Integer num);

    List<Product> getHotListCache();
}
