package com.qubaolai.common.basic;


import com.qubaolai.po.Employee;

public interface BaseService {
    /**
     * 获取当前登陆用户
     */
    public Employee getCurrentLoginEmployee();
}
