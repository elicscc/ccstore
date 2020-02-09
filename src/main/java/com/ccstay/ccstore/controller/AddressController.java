package com.ccstay.ccstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ccstay.ccstore.entity.Address;
import com.ccstay.ccstore.service.IAddressService;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    IAddressService service;

    @RequestMapping("create_address")
    public JsonResult<Void> createAddress(Address address, HttpSession session) {

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.createAddress(uid, username, address);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("list")
    public JsonResult<List<Address>> listByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        return new JsonResult<List<Address>>(SUCCESS, service.listByUid(uid));
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.setDefault(aid, uid, username);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.removeAddress(aid, uid, username);
        return new JsonResult<>(SUCCESS);

    }
}
