package cn.fb.service;

import cn.fb.pojo.User;

public interface UserService {
    User login(String loginname, String password);
}
