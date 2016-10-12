package com.xujinxin.datasync.service;

import com.xujinxin.datasync.bean.User;

import java.util.List;


public interface UserService {
    User selectUserById(Integer id);

    List<User> selectAll();

    List<User> selectAllByJdbcTemplate();
}
