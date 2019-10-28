package com.guanghua.common.basic.impl;

import com.guanghua.common.basic.BaseService;
import com.guanghua.po.Employee;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseServiceImpl implements BaseService {

    @Resource
    private HttpServletRequest request;
    @Override
    public Employee getCurrentLoginEmployee() {
        if(request != null){
            HttpSession session = request.getSession();
            Employee user = (Employee) session.getAttribute("user");
            return user;
        }
        return null;
    }
}
