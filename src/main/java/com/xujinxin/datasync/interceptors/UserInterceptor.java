package com.xujinxin.datasync.interceptors;

import com.xujinxin.datasync.bean.User;
import com.xujinxin.datasync.exception.ValidException;
import com.xujinxin.datasync.enums.ErrorType;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类针对/user下所有的接口可以做拦截，权限控制之类的
 */

@Service
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = new User();
        Map<String, String> requestBody = new HashMap<String, String>();
        //获取请求参数信息
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestBody.put(parameterName, request.getParameterValues(parameterName)[0]);
        }
        //实现map转对象
        BeanUtils.populate(user, requestBody);
        //如果请求信息为空或者用户不是老大，就抛出权限未开启
        if (requestBody.isEmpty() || !"Boss".equals(user.getUsername())) {
            throw new ValidException(ErrorType.USER_NOT_REGISTER);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
