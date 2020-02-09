package com.ccstay.ccstore.service;

import java.util.List;

import com.ccstay.ccstore.entity.District;

/**
 * 省市区业务层
 * 
 * @author Alex
 *
 */
public interface IDistrictService {
    /**
     * 根据父级编号查询
     * 
     * @param parent
     * @return
     */
    List<District> listByParent(String parent);

    District getByCode(String code);
}
