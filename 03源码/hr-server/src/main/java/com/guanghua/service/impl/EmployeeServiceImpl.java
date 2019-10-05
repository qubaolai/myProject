package com.guanghua.service.impl;

import com.guanghua.mapper.EmployeeMapper;
import com.guanghua.po.Employee;
import com.guanghua.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public Employee selectEmployeeById(String id) {
        return employeeMapper.selectByPrimaryKey(id);
    }
}
