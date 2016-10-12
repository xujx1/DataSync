package com.xujinxin.datasync.dao;

import com.xujinxin.datasync.bean.User;

import java.util.List;

public interface UserDao {
    User selectUserById(Integer id);

    List<User> selectAll();
}
