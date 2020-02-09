package com.ccstay.ccstore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ccstay.ccstore.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("web/pay")
public class PayController extends BaseController {
    @Autowired
    IPayService service;

//    @RequestMapping("money")
//    public JsonResult<String> pay(Integer id)  {
//        String re = service.pay(id);
//        return new JsonResult<String>(SUCCESS, re);
//    }

    @RequestMapping("money")
    public void pay(Integer id, HttpServletResponse response) throws IOException {
        String re = service.pay(id);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(re);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

}
