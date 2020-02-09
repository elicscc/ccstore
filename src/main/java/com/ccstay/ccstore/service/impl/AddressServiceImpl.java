package com.ccstay.ccstore.service.impl;

import java.util.Date;
import java.util.List;

import com.ccstay.ccstore.entity.Address;
import com.ccstay.ccstore.entity.District;
import com.ccstay.ccstore.mapper.AddressMapper;
import com.ccstay.ccstore.service.IAddressService;
import com.ccstay.ccstore.service.IDistrictService;
import com.ccstay.ccstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper mapper;
    @Autowired
    IDistrictService districtService;

    @Override
    public void createAddress(Integer uid, String username, Address address)
            throws AddressCountLimitException, InsertException {
        // 根据uid查询收货地址条数
        Integer count = countByUid(uid);
        // 条数是否达到上限 3
        if (count >= ADDRESS_MAX_COUNT) {
            throw new AddressCountLimitException("地址达到上限");
        }
        // 是：AddressCountLimitException
        address.setUid(uid);

        // 补全isDefault，根据上面查询到的收货地址条数进行判断
        int isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        // TODO 补全省市区数据：补充省市区名称
        if (address.getZip() == null) {
            address.setZip(0);
        }
        String province = getNameByCode(address.getProvinceCode().toString());
        String city = getNameByCode(address.getCityCode().toString());
        String area = getNameByCode(address.getAreaCode().toString());
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);

        // 创建当前时间对象
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        // 补全4项日志数据
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        // 执行添加操作
        saveAddress(address);
    }

    @Override
    public List<Address> listByUid(Integer uid) {
        return findByUid(uid);
    }

    private void saveAddress(Address address) {
        Integer row = mapper.saveAddress(address);
        // Integer 类型在范围[-128,127] 范围之内可以判断 否则要equals
        if (row != 1) {
            throw new InsertException("添加地址异常");
        }
    }

    private Integer countByUid(Integer uid) {
        if (uid == null || uid <= 0) {
            throw new IllegalArgumentException();
        }
        return mapper.countByUid(uid);
    }

    private String getNameByCode(String code) {
        District dist = districtService.getByCode(code);
        return dist == null ? "" : dist.getName();

    }

    private List<Address> findByUid(Integer uid) {
        List<Address> list = mapper.findByUid(uid);
        for (Address addr : list) {
            addr.setZip(null);
            addr.setTel(null);
            addr.setCreatedUser(null);
            addr.setCreatedTime(null);
            addr.setModifiedUser(null);
            addr.setModifiedTime(null);
        }
        return list;
    }

    @Override
    @Transactional // @Transactional 默认只对RuntimeException回滚 可以配置rollbackfor =exception.class
    public void setDefault(Integer aid, Integer uid, String username)
            throws AddressNotFoundException, AccessDeniedException, UpdateException {
        // 使用aid查地址数据
        /*
         * Address addr = findByAid(aid);
         * 
         * // 判断结果是否为null if (addr == null) { throw new
         * AddressNotFoundException("地址找不到"); } // 是：AddressNotFoundException
         * 
         * // 查询结果中的uid和方法参数的uid是否不一致 if (!addr.getUid().equals(uid)) { throw new
         * AccessDeniedException("非法请求！！"); }
         */
        // 是：AccessDeniedException

        // 将该用户的所有收货地址设为非默认
        updateNonDefaul(uid);

        // 将该用户指定的收货地址设为默认
        updateDefault(aid, username);

    }

    @Override
    @Transactional
    public void removeAddress(Integer aid, Integer uid, String username) {
        Address addr = findByAid(aid);
        if (addr == null) {
            throw new AddressNotFoundException("地址未找到");
        }
        if (!addr.getUid().equals(uid)) {
            throw new AccessDeniedException("非法请求！！");
        }
        deleteByAid(aid);
        if (!addr.getIsDefault().equals(1)) {
            return;
        }
        Address address = findLastModified(uid);
        if (address != null) {
            setDefault(address.getAid(), uid, username);
        }

    }

    @Override
    public Address getByAid(Integer aid) {
        return findByAid(aid);
    }

    private Address findByAid(Integer aid) {
        return mapper.findByAid(aid);
    }

    private Address findLastModified(Integer uid) {
        Address address = mapper.findLastModified(uid);
        return address;
    }

    private void deleteByAid(Integer aid) {
        Integer row = mapper.deleteByAid(aid);
        if (row != 1) {
            throw new DeleteException("删除地址异常");
        }
    }

    private void updateNonDefaul(Integer uid) {
        Integer row = mapper.updateNonDefault(uid);
        if (row < 1) {
            throw new UpdateException("设置默认地址异常！");
        }
    }

    private Integer updateDefault(Integer aid, String username) {
        return mapper.updateDefault(aid, username, new Date());
    }

}