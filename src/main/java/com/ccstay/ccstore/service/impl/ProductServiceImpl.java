package com.ccstay.ccstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ccstay.ccstore.controller.ex.ProductNotFoundException;
import com.ccstay.ccstore.entity.Product;
import com.ccstay.ccstore.mapper.ProductMapper;
import com.ccstay.ccstore.service.IProductService;
import com.ccstay.ccstore.service.ex.ProductOutOfStockException;
import com.ccstay.ccstore.service.ex.UpdateException;
import com.ccstay.ccstore.util.ObjectMapperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;


@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper mapper ;
   //@Autowired
    private Jedis jedis;

    @Override
    public List<Product> getHotList() {
        return findHotList();
    }

    @Override
    public List<Product> getNewList() {
        return findNewList();
    }

    @Override
    public Product findById(Integer id) {
        Product product = ById(id);
        if (product == null) {
            throw new ProductNotFoundException("未找到商品数据！");
        }
        product.setCreatedTime(null);
        product.setCreatedUser(null);
        product.setModifiedTime(null);
        product.setModifiedUser(null);
        product.setItemType(null);
        product.setStatus(null);
        return product;
    }

    @Override
    public void reduceNum(Integer pid, Integer num) {
        // 根据pid查询商品数据
        Product product = findById(pid);
        // 判断结果是否为null
        if (product == null) {
            throw new ProductNotFoundException("减少商品数量异常，商品不存在！");
        }
        // 是：ProductNotFoundException

        // 从查询结果中获取当前库存量
        Integer newNum = product.getNum() - num;

        // 计算库存减少后的结果
        // 判断该结果是否小于0
        if (newNum < 0) {
            throw new ProductOutOfStockException("商品库存小于0!");
        }
        // 是：ProductOutOfStockException

        // 执行更新操作
        updateNum(num, pid);
    }

    @Override
    public void addNum(Integer pid, Integer num) {
        // 根据pid查询商品数据
        Product product = findById(pid);
        // 判断结果是否为null
        if (product == null) {
            throw new ProductNotFoundException("还原商品数量异常，商品不存在！");
        }
        // 是：ProductNotFoundException

        // 从查询结果中获取当前库存量
        Integer newNum = product.getNum() + num;

        // 计算库存减少后的结果
        // 判断该结果是否小于0
        if (newNum < 0) {
            throw new ProductOutOfStockException("商品库存小于0!");
        }
        // 是：ProductOutOfStockException

        // 执行更新操作
        updateNum(num, pid);
    }

    @Override
    public List<Product> getHotListCache() {
        List<Product> hotList=new ArrayList<>();
        String result = jedis.get("HotList::0");
        if (StringUtils.isEmpty(result)){
            hotList = findHotList();
            String dateJson= ObjectMapperUtil.toJson(hotList);
            jedis.set("HotList::0",dateJson);
           // System.err.println("第一次需要查询数据库");
        }else {
            hotList= ObjectMapperUtil.toObject(result,hotList.getClass());
            //System.err.println("查询了缓存数据");
        }
        return hotList;
    }

    private List<Product> findNewList() {
        return mapper.findNewList();
    }

    private List<Product> findHotList() {
        return mapper.findHotList();
    }

    private Product ById(Integer id) {
        return mapper.findById(id);
    }

    private void updateNum(Integer num, Integer id) {
        Integer row = mapper.updateNum(num, id);
        if (row != 1) {
            throw new UpdateException("更新库存数量失败！更新异常！");
        }
    }
}
