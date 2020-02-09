package com.ccstay.ccstore.service.impl;

import java.util.List;

import com.ccstay.ccstore.entity.District;
import com.ccstay.ccstore.mapper.DistrictMapper;
import com.ccstay.ccstore.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    DistrictMapper mapper;

    @Override
    public List<District> listByParent(String parent) {
        return findByParent(parent);
    }

    private List<District> findByParent(String parent) {
        return mapper.findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
        return findByCode(code);
    }

    private District findByCode(String code) {
        return mapper.findByCode(code);
    }
}
