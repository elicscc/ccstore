package com.ccstay.ccstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ccstay.ccstore.entity.Cart;
import com.ccstay.ccstore.entity.CartVO;
import com.ccstay.ccstore.mapper.CartMapper;
import com.ccstay.ccstore.service.ICartService;
import com.ccstay.ccstore.service.IProductService;
import com.ccstay.ccstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper mapper;
    @Autowired
    IProductService service;

    @Override
    public List<CartVO> getCartList(Integer uid) {
        return findCartList(uid);
    }

    @Override
    public void createCart(Integer num, Integer uid, Integer pid, String username)
            throws UpdateException, InsertException {
        // 使用uid和pid查询是否有购物车数据
        Cart cart = getByUidAndPid(uid, pid);

        // 没有：
        if (cart == null) {
            // 创建一个Cart对象
            Cart newcart = new Cart();
            // 手动添加pid,uid,num到cart
            newcart.setPid(pid);
            newcart.setUid(uid);
            newcart.setNum(num);
            // 使用pid查询商品价格
            Long price = service.findById(pid).getPrice();
            newcart.setPrice(price);
            // 手动添加商品价格到cart
            // 手动添加4个日志数据到cart
            newcart.setCreated_user(username);
            newcart.setModified_user(username);
            newcart.setCreated_time(new Date());
            newcart.setModified_time(new Date());
            // 将cart添加到数据库
            saveCart(newcart);
            return;
        }

        Integer cid = cart.getCid();
        // 从查询结果中获取cid
        // 从查询结果中获取原购物车中的数量
        Integer count = cart.getNum() + num;
        // 计算出新的商品数量=原数量+num
        // 执行更新操作
        updateNum(cid, count, username);

    }

    @Override
    public void removeCart(Integer cid, Integer uid)
            throws DeleteException, CartNotFoundException, AccessDeniedException {
        Cart cart = findByCid(cid);
        if (cart == null) {
            throw new CartNotFoundException("删除购物车记录异常！未查到购物车记录");
        }
        if (!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("删除购物车记录异常！用户无操作权限");
        }
        deleteByCid(cid);
    }

    @Override
    public void addNum(Integer cid, Integer num, Integer uid, String username)
            throws CartNotFoundException, AccessDeniedException, UpdateException {
        Cart cart = findByCid(cid);
        if (cart == null) {
            throw new CartNotFoundException("未查到购物车记录");
        }
        if (!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("用户无操作权限");
        }
        Integer n = cart.getNum() + num;
        if (n <= 0) {
            return;
        }
        updateNum(cid, n, username);
    }

    @Override
    public void reduceNum(Integer cid, Integer num, Integer uid, String username)
            throws CartNotFoundException, AccessDeniedException, UpdateException {

    }

    private void deleteByCid(Integer cid) {
        Integer row = mapper.deleteByCid(cid);
        if (row != 1) {
            throw new DeleteException("删除购物车异常");
        }
    }

    @Override
    public List<CartVO> getByCids(Integer[] cids, Integer uid) {

        if (cids == null) {
            return new ArrayList<CartVO>();
        }
        List<CartVO> result = findByCids(cids);
        Iterator<CartVO> it = result.iterator();
        while (it.hasNext()) {
            CartVO vo = it.next();
            if (!vo.getUid().equals(uid)) {
                it.remove();
            }
        }
        return result;
    }

    @Override
    public void removeByCids(Integer[] cids, Integer uid) {
        // 需要验证cids的正确性
        // 判断cids是否为null
        if (cids == null) {
            throw new IllegalArgumentException("参数异常");
        }
        List<CartVO> cartVOs = findByCids(cids);
        if (cartVOs.size() == 0) {
            throw new CartNotFoundException("删除购物车异常！未找到对应记录");
        }
        // 需要验证数据的归属性
        List<Integer> rightCids = new ArrayList<Integer>();
        for (CartVO vo : cartVOs) {
            if (vo.getUid().equals(uid)) {
                rightCids.add(vo.getCid());
            }
        }
        // 执行删除操作
        deleteByCids(rightCids.toArray(new Integer[rightCids.size()]));
    }

    /**
     * 根据cid查询购物车
     * 
     * @param cid
     * @return
     */
    private Cart findByCid(Integer cid) {
        return mapper.findByCid(cid);
    }

    /**
     * 添加一条购物车记录
     * 
     * @param cart 购物车记录
     * 
     */
    private void saveCart(Cart cart) {
        Integer row = mapper.saveCart(cart);
        if (row != 1) {
            throw new InsertException("添加购物车异常");
        }
    }

    /**
     * 修改购物车商品数量
     * 
     * @param cid
     * @param num
     * @param username
     */
    private void updateNum(Integer cid, Integer num, String username) {
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        Integer row = mapper.updateNum(cid, num, username, new Date());
        if (row != 1) {
            throw new UpdateException("修改购物车异常");
        }
    }

    private Cart getByUidAndPid(Integer uid, Integer pid) {
        return mapper.getByUidAndPid(uid, pid);
    }

    private List<CartVO> findCartList(Integer uid) {
        return mapper.findCartList(uid);
    }

    /**
     * 根据一组cid查询对应的购物车记录
     * 
     * @param cids 一组购物车记录的id
     * @return 购物车记录数据
     */
    private List<CartVO> findByCids(Integer[] cids) {
        return mapper.findByCids(cids);
    }

    private void deleteByCids(Integer[] cids) throws DeleteException {
        if (cids == null || cids.length == 0) {
            throw new IllegalArgumentException("删除购物车记录异常！参数异常！");
        }
        Integer rows = mapper.deleteByCids(cids);
        if (rows < 1) {
            throw new DeleteException("删除购物车记录异常！请联系管理员");
        }
    }
}
