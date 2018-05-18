package cn.fb.service.Impl;

import cn.fb.mapper.UserMapper;
import cn.fb.pojo.User;
import cn.fb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String loginname, String password) {
        return userMapper.selectByLogin(loginname,password);
    }
}
