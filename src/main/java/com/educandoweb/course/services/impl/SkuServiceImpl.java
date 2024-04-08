package com.educandoweb.course.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.educandoweb.course.entities.Sku;
import com.educandoweb.course.mapper.SkuMapper;
import com.educandoweb.course.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {


    @Autowired
    private SkuMapper mapper;


    @Override
    public List<Sku> findAll(LambdaQueryWrapper<Sku> lambdaQueryWrapper) {
        return mapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Sku findById(Long id) {
        Sku obj = mapper.selectById(id);
        return obj;
    }

    @Override
    public Sku save(Sku sku) {
        int insert = mapper.insert(sku);
        return sku;
    }
}
