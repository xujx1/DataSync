package com.xujinxin.datasync.service.impl;

import com.xujinxin.datasync.bean.User;
import com.xujinxin.datasync.dao.UserDao;
import com.xujinxin.datasync.service.UserService;
import com.xujinxin.datasync.util.redis.RedisOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisOperationService redisOperationService;

    public User selectUserById(Integer id) {
        User user = redisOperationService.getCache(String.valueOf(id), User.class);
        if (null == user) {
            user = userDao.selectUserById(id);
        }
        return user;
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public List<User> selectAllByJdbcTemplate() {
        return jdbcTemplate.query("select * from USER ", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }
}
