package com.ccstay.ccstore.mapper;

import java.util.Date;

import com.ccstay.ccstore.entity.Order;
import com.ccstay.ccstore.entity.OrderItem;
import org.apache.ibatis.annotations.Param;



/**
 * 订单数据持久层接口
 * 
 * @author Alex
 *
 */
public interface OrderMapper {
    /**
     * 保存一条订单详情数据
     * 
     * @param order
     * @return
     */
    Integer saveOrder(Order order);

    /**
     * 保存一条订单项数据
     * 
     * @param orderItem
     * @return
     */
    Integer saveOrderItem(OrderItem orderItem);

    /**
     * 修改订单状态
     * 
     * @return
     */
    Integer updateStatus(@Param("id") Integer id, @Param("status") Integer status, @Param("username") String username,
                         @Param("modifiedTime") Date modifiedTime);

    Order findById(Integer id);

    Order findByUid(Integer uid);
}
