package com.guanghua.common.basic;

import com.guanghua.po.Employee;

public interface BaseService {
    /**
     * 获取当前登陆用户
     */
    public Employee getCurrentLoginEmployee();
}
