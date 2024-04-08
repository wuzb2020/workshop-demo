package com.educandoweb.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educandoweb.course.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
