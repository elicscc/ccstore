package com.ccstay.ccstore.mapper;

import java.util.List;

import com.ccstay.ccstore.entity.Product;
import org.apache.ibatis.annotations.Param;


public interface ProductMapper {
    List<Product> findHotList();

    List<Product> findNewList();

    Product findById(Integer id);

    Integer updateNum(@Param("num") Integer num, @Param("id") Integer id);
}
