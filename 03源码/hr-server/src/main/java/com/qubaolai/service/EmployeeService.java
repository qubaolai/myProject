package com.qubaolai.service;

import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.vo.ResultVo;

/**
 * @author qubaolai
 * @date 2019/10/30 14:03
 */
public interface EmployeeService {
    public ResultVo login(Employee employee);
}
