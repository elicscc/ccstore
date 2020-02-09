package com.ccstay.ccstore.mapper;

import java.util.Date;
import java.util.List;

import com.ccstay.ccstore.entity.Cart;
import com.ccstay.ccstore.entity.CartVO;
import org.apache.ibatis.annotations.Param;


public interface CartMapper {
    Integer saveCart(Cart cart);

    Integer updateNum(@Param("cid") Integer cid, @Param("num") Integer num, @Param("modifiedUser") String username,
                      @Param("modifiedTime") Date modifiedTime);

    Cart getByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    /**
     * 查询一个用户的购物车所有记录
     * 
     * @param uid
     * @return
     */
    List<CartVO> findCartList(Integer uid);

    Cart findByCid(Integer cid);

    /**
     * 根据cid删除一条购物车记录
     * 
     * @param cid 购物车id
     * @return 返回受影响的行数
     */
    Integer deleteByCid(Integer cid);

    List<CartVO> findByCids(Integer[] cids);

    Integer deleteByCids(Integer[] cids);

}
