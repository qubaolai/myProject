package com.qubaolai.common.basic.impl;

import com.qubaolai.common.basic.BaseService;
import com.qubaolai.po.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
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
