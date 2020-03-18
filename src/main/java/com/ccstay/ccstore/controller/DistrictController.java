package com.ccstay.ccstore.controller;


import com.ccstay.ccstore.entity.District;
import com.ccstay.ccstore.service.impl.DistrictServiceImpl;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController {
    @Autowired
    DistrictServiceImpl service;

    @RequestMapping("/")
    public JsonResult<List<District>> listByParent(String parent) {
        return new JsonResult<List<District>>(SUCCESS, service.listByParent(parent));
    }
}
