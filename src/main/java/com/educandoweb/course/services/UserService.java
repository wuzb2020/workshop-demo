package com.educandoweb.course.services;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educandoweb.course.entities.User;

public interface UserService{

    User findById(Long id);
}
