package com.ccstay.ccstore.mapper;

import java.util.Date;
import java.util.List;


import com.ccstay.ccstore.entity.Address;
import org.apache.ibatis.annotations.Param;


/**
 * 地址功能的持久层接口
 */
public interface AddressMapper {
    /**
     * 新增加收货地址
     * 
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer saveAddress(Address address);

    /**
     * 根据uid查询用户收货地址条数
     * 
     * @param uid 用户id
     * @return 返回地址条数
     */
    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Integer updateNonDefault(Integer uid);

    Address findByAid(Integer aid);

    Integer updateDefault(@Param("aid") Integer aid, @Param("modifiedUser") String modifiedUser,
                          @Param("modifiedTime") Date modifiedTime);

    Integer deleteByAid(Integer aid);
    
    Address findLastModified(Integer uid);
}
