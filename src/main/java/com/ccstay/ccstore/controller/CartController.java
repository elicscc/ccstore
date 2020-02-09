package com.ccstay.ccstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ccstay.ccstore.entity.CartVO;
import com.ccstay.ccstore.service.ICartService;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    ICartService service;

    @PostMapping("create_cart")
    public JsonResult<Void> createCart(Integer num, Integer pid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.createCart(num, uid, pid, username);
        return new JsonResult<Void>(SUCCESS);
    }

    @PostMapping("{cid}/del")
    public JsonResult<Void> removeCart(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        service.removeCart(cid, uid);
        return new JsonResult<Void>(SUCCESS);
    }

    @GetMapping("/")
    public JsonResult<List<CartVO>> carts(HttpSession session) {
        Integer uid = getUidFromSession(session);
        return new JsonResult<List<CartVO>>(SUCCESS, service.getCartList(uid));
    }

    @RequestMapping("add_num")
    public JsonResult<List<CartVO>> addNum(HttpSession session, Integer num, Integer cid) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.addNum(cid, num, uid, username);
        return new JsonResult<List<CartVO>>(SUCCESS);
    }

    @GetMapping("get_by_cids")
    public JsonResult<List<CartVO>> getByCids(HttpSession session, Integer[] cids) {
        Integer uid = getUidFromSession(session);
        List<CartVO> data = service.getByCids(cids, uid);
        return new JsonResult<List<CartVO>>(SUCCESS,data);
    }

}
