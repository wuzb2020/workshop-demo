package com.educandoweb.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educandoweb.course.entities.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper  extends BaseMapper<Order> {
}
