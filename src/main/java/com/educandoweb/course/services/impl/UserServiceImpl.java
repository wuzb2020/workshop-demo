package com.educandoweb.course.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.mapper.UserMapper;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.selectList(new LambdaQueryWrapper());
    }

    @Override
    public User findById(Long id) {
        User obj = userMapper.selectById(id);
        return obj;
    }
}
