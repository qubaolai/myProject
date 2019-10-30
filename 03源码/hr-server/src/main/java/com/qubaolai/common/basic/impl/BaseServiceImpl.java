package com.qubaolai.common.basic.impl;

import com.qubaolai.common.basic.BaseService;
import com.qubaolai.po.Employee;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseServiceImpl implements BaseService {

    @Resource
    private HttpServletRequest request;

    public Employee getCurrentLoginEmployee() {
        if (request != null) {
            HttpSession session = request.getSession();
            Employee user = (Employee) session.getAttribute("user");
            return user;
        }
        return null;
    }
}
