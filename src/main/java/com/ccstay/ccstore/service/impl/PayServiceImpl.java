package com.ccstay.ccstore.service.impl;


import com.ccstay.ccstore.entity.Order;
import com.ccstay.ccstore.pay.AlipayConfig;
import com.ccstay.ccstore.service.IOrderService;
import com.ccstay.ccstore.service.IPayService;
import com.ccstay.ccstore.service.ex.PayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;



@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    IOrderService service;

    private static AlipayClient alipayClient;
    private static AlipayTradePagePayRequest alipayRequest;
    static {
        alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        alipayRequest = new AlipayTradePagePayRequest();
    }

    @Override
    public String pay(Integer payId) {

        // 设置请求参数
        if (payId==null) {
           throw new PayException("支付异常！id为空") ;
        }
        Order order = service.getById(payId);
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        // String out_trade_no = new
        // String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        // 付款金额，必填
        // String total_amount = new
        // String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        // 订单名称，必填
        // String subject = new
        // String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        // 商品描述，可空
        // String body = new
        // String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

//        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
//                + "\"total_amount\":\""+ total_amount +"\"," 
//                + "\"subject\":\""+ subject +"\"," 
//                + "\"body\":\""+ body +"\"," 
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String title="CC商城支付测试";
        // 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + payId + "\"," + "\"total_amount\":\"" + order.getPrice() + "\","
                + "\"subject\":\"" + title + "\","
                // + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"15m\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        // 请求
        return pageExecute(alipayRequest);
    }

    private String pageExecute(AlipayTradePagePayRequest alipayRequest) {

        String repay;
        try {
            repay = alipayClient.pageExecute(alipayRequest).getBody();
            return repay;
        } catch (AlipayApiException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
        return null;

    }

}
