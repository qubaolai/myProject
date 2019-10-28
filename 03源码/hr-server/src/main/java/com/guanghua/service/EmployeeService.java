package com.guanghua.service;

import com.guanghua.po.Employee;
import com.guanghua.vo.ResultVo;

/**
 * @author qubaolai
 * @date 2019/10/27 19:01
 */
public interface EmployeeService {
    /**
     *  用户登录
     */
    public ResultVo login(Employee employee);
}
