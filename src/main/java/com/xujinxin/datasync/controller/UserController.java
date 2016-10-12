package com.xujinxin.datasync.controller;

import com.xujinxin.datasync.service.UserService;
import com.xujinxin.datasync.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo selectAll() {
        return new ResponseVo.Builder().data(userService.selectAll()).build();
    }
}
