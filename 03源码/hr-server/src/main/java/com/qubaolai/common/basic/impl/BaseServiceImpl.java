package com.qubaolai.common.basic.impl;

import com.qubaolai.common.basic.BaseService;
import com.qubaolai.po.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public Employee getCurrentLoginEmployee() {
        if(httpServletRequest != null){
            HttpSession session = httpServletRequest.getSession();
            Employee user = (Employee) session.getAttribute("user");
            return user;
        }
        return null;
    }
}
