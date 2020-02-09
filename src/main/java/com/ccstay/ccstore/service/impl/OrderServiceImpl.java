package com.ccstay.ccstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccstay.ccstore.entity.Address;
import com.ccstay.ccstore.entity.CartVO;
import com.ccstay.ccstore.entity.Order;
import com.ccstay.ccstore.entity.OrderItem;
import com.ccstay.ccstore.mapper.OrderMapper;
import com.ccstay.ccstore.service.IAddressService;
import com.ccstay.ccstore.service.ICartService;
import com.ccstay.ccstore.service.IOrderService;
import com.ccstay.ccstore.service.IProductService;
import com.ccstay.ccstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ICartService cartService;
    @Autowired
    IAddressService addressService;
    @Autowired
    IProductService productService;

    @Override
    @Transactional
    public void createOrder(Integer aid, Integer[] cids, Integer uid, String username) throws ServiceException {
        Date now = new Date();
        // 创建当前时间的对象 now

        // 根据cids查询对应的CartVO的集合
        List<CartVO> list = cartService.getByCids(cids, uid);
        // 判断结果集合的长度是否为0
        if (list.size() == 0) {
            throw new CartNotFoundException("创建订单记录异常！未找到相关购物车记录");
        }
        // 是：CartNotFoundException

        // 计算totalPrice
        Long totalPrice = 0L;
        for (CartVO cartVO : list) {
            totalPrice += cartVO.getRealPrice() * cartVO.getNum();
        }
        // 创建一个Order对象

        Order order = new Order();

        // 补充uid
        order.setUid(uid);
        // 根据aid查询收获地址数据
        Address address = addressService.getByAid(aid);

        // 判断结果是否为null
        if (address == null) {
            throw new AddressNotFoundException("地址未找到");
        }
        // 是：AddressNotFoundException

        // 补充recv*数据
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());

        // 补充status -> 0
        order.setStatus(0);
        // 补充price -> 总价，在上面已经计算过
        order.setPrice(totalPrice);
        // 补充orderTime -> now
        order.setOrderTime(now);
        order.setCreatedTime(now);
        order.setModifiedTime(now);
        order.setCreatedUser(username);
        order.setModifiedUser(username);

        // 添加订单数据
        saveOrder(order);
        // 遍历上面查到的CartVO的集合
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartVO vo : list) {
            // -- 创建一个OrderItem对象
            // -- 补充 oid
            // -- 补充 pid,num,price,image,titel
            // -- 补充 4项日志数据
            // -- 添加一个OrderItem数据
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getId());
            orderItem.setPid(vo.getPid());
            orderItem.setImage(vo.getImage());
            orderItem.setNum(vo.getNum());
            orderItem.setTitle(vo.getTitle());
            orderItem.setPrice(vo.getPrice());
            orderItem.setCreatedTime(now);
            orderItem.setCreatedUser(username);
            orderItem.setModifiedTime(now);
            orderItem.setModifiedUser(username);
            saveOrderItem(orderItem);
            orderItems.add(orderItem);
            productService.reduceNum(vo.getPid(), vo.getNum());

        }
        cartService.removeByCids(cids, uid);
        // 处理超时未支付
        // 启动子线程，休眠15分钟
        // 在子线程醒来之后，执行关闭订单的操作
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.err.println("子线程准备休眠...");
//                try {
//                    Thread.sleep(30 * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.err.println("子线程启动，执行关闭订单操作...");
//                // 调用关闭订单的方法
//                close(order.getId(), orderItems, username);
//            }
//        }).start();

    }

    @Override
    public void close(Integer oid, List<OrderItem> orderItems, String username) {
        // 根据oid获取订单数据
        Order order = findById(oid);
        // 判断结果是否为null
        if (order == null) {
            throw new OrderNotFoundException("关闭订单异常，订单不存在！");
        }
        // 是：OrderNotFoundException

        // 判断订单状态是否不为0
        if (order.getStatus() != 0) {
            return;
        }
        // 是：return;

        // 修改订单状态 status->3
        changeStatus(oid, Status.CLOSED, username);
        // 归还库存

        // 遍历orderItems
        for (OrderItem orderItem : orderItems) {
            productService.addNum(orderItem.getPid(), orderItem.getNum());
        }
        // -- 获取pid和num
        // -- 调用增加库存的方法 addNum(pid,num);
    }

    @Override
    public Order getById(Integer oid) {
        return findById(oid);
    }

    @Override
    public Order getByUid(Integer uid) {
        return findByUid(uid);
    }

    private void saveOrder(Order order) throws InsertException {
        Integer row = orderMapper.saveOrder(order);
        if (row != 1) {
            throw new InsertException("插入订单失败！");
        }
    }

    private void saveOrderItem(OrderItem orderItem) throws InsertException {
        Integer row = orderMapper.saveOrderItem(orderItem);
        if (row != 1) {
            throw new InsertException("插入订单项失败！");
        }
    }

    @Override
    public void changeStatus(Integer oid, Integer status, String username) {

        // 使用oid查询订单数据
        Order order = findById(oid);
        // 判断结果是否为Null
        if (order == null) {
            throw new OrderNotFoundException("更新订单状态异常！订单数据不存在!");
        }
        // 是： OrderNotFoundException

        // 更新订单状态
        updateStatus(oid, status, username, new Date());
    }

    private Order findById(Integer id) {
        return orderMapper.findById(id);
    }

    private void updateStatus(Integer id, Integer status, String username, Date modifiedTime) {
        Integer row = orderMapper.updateStatus(id, status, username, modifiedTime);
        if (row != 1) {
            throw new UpdateException("修改订单状态失败");
        }
    }

    private Order findByUid(Integer id) {

        return orderMapper.findByUid(id);
    }
}
