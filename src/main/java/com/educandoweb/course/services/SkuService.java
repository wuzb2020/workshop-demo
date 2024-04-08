package com.educandoweb.course.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Sku;

import java.util.List;

public interface SkuService {
    List<Sku> findAll(LambdaQueryWrapper<Sku> lambdaQueryWrapper);

    Sku findById(Long id);

    Sku save(Sku skuId);
}
