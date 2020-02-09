package com.ccstay.ccstore.service;

import com.ccstay.ccstore.entity.CartVO;
import com.ccstay.ccstore.service.ex.*;

import java.util.List;



/**
 * 购物车业务
 * 
 * @author Alex
 *
 */
public interface ICartService {
    void createCart(Integer num, Integer uid, Integer pid, String username) throws UpdateException, InsertException;

    List<CartVO> getCartList(Integer uid);

    /**
     * 
     * @param cid      商品的id
     * @param num      数量的增量
     * @param uid      用户的id
     * @param username 最后修改人姓名
     */
    void addNum(Integer cid, Integer num, Integer uid, String username)
            throws CartNotFoundException, AccessDeniedException, UpdateException;

    void removeCart(Integer cid, Integer uid) throws DeleteException, CartNotFoundException, AccessDeniedException;

    /**
     * 减少购物车商品的数量
     * 
     * @param cid
     * @param num
     * @param uid
     * @param username
     * @throws CartNotFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    void reduceNum(Integer cid, Integer num, Integer uid, String username)
            throws CartNotFoundException, AccessDeniedException, UpdateException;

    List<CartVO> getByCids(Integer[] cids, Integer uid);

    /**
     * 根据一组id删除购物车记录
     * 
     * @param cids
     * @param uid
     */
    void removeByCids(Integer[] cids, Integer uid);
}
