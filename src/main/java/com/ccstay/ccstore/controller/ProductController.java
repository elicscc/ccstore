package com.ccstay.ccstore.controller;

import java.util.List;

import com.ccstay.ccstore.entity.Product;
import com.ccstay.ccstore.service.IProductService;
import com.ccstay.ccstore.util.Cache_Find;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    IProductService service;

    @GetMapping("hot")
    @Cache_Find
    public JsonResult<List<Product>> getHostList() {
        List<Product> data = service.getHotList();
       // List<Product> data = service.getHotListCache();
        return new JsonResult<List<Product>>(SUCCESS, data);
    }

    @GetMapping("new")
    @Cache_Find
    public JsonResult<List<Product>> getNewList() {
        List<Product> data = service.getNewList();
        return new JsonResult<List<Product>>(SUCCESS, data);
    }

    @GetMapping("{id}/get")
    public JsonResult<Product> findById(@PathVariable("id") Integer id) {
        Product product = service.findById(id);
        return new JsonResult<Product>(SUCCESS, product);
    }
}
