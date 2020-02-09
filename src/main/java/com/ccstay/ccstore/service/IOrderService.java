package com.ccstay.ccstore.service;

import com.ccstay.ccstore.entity.Order;
import com.ccstay.ccstore.entity.OrderItem;
import com.ccstay.ccstore.service.ex.AddressNotFoundException;
import com.ccstay.ccstore.service.ex.CartNotFoundException;
import com.ccstay.ccstore.service.ex.InsertException;

import java.util.List;



/**
 * 订单管理业务
 * 
 * @author Alex
 *
 */
public interface IOrderService {

    static interface Status {
        int UNPAID = 0;
        int PAID = 1;
        int CANCLED = 2;
        int CLOSED = 3;
    }

    void createOrder(Integer aid, Integer[] cids, Integer uid, String username)
            throws InsertException, CartNotFoundException, AddressNotFoundException;

    /**
     * 更新一个订单的状态
     * 
     * @param oid
     * @param status
     * @param username
     */
    void changeStatus(Integer oid, Integer status, String username);

    void close(Integer oid, List<OrderItem> orderItems, String username);

    Order getById(Integer oid);
    
    Order getByUid(Integer uid);
}
