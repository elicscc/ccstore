package com.ccstay.ccstore.mapper;

import com.ccstay.ccstore.entity.District;

import java.util.List;


/**
 * 省市区的持久层接口
 */
public interface DistrictMapper {
    List<District> findByParent(String parent);

    District findByCode(String code);
}
