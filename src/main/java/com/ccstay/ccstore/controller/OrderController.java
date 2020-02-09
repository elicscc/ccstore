package com.ccstay.ccstore.controller;


import javax.servlet.http.HttpSession;

import com.ccstay.ccstore.entity.Order;
import com.ccstay.ccstore.service.IOrderService;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    IOrderService service;

    @PostMapping("create")
    public JsonResult<Void> createOrder(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.createOrder(aid, cids, uid, username);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("id")
    public JsonResult<Order> carts(HttpSession session) {
        Integer uid = getUidFromSession(session);
        return new JsonResult<Order>(SUCCESS, service.getByUid(uid));
    }

}
