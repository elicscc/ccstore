package com.ccstay.ccstore.service;

import java.util.List;


import com.ccstay.ccstore.entity.Address;
import com.ccstay.ccstore.service.ex.*;

/**
 * 地址管理业务
 * 
 * @author Alex
 *
 */
public interface IAddressService {
    /**
     * 地址数量上限
     */
    int ADDRESS_MAX_COUNT = 3;

    void createAddress(Integer uid, String username, Address address)
            throws AddressCountLimitException, InsertException;

    List<Address> listByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username)
            throws AddressNotFoundException, AccessDeniedException, UpdateException;

    void removeAddress(Integer aid, Integer uid, String username)
            throws AddressNotFoundException, AccessDeniedException, DeleteException, UpdateException;

    /**
     * 根据收货地址id获取收获地址数据
     * 
     * @param aid 收货地址id
     * @return 收获地址数据
     */
    Address getByAid(Integer aid);
}
