package com.qubaolai.serve.impl;

import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.serve.EmployeeServe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qubaolai
 * @date 2019/10/29 11:49
 */
@Service
public class EmployeeServeImpl implements EmployeeServe {
    @Resource
    private EmployeeMapper employeeMapper;
    public List<Employee> selectEmp(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(employee.getEmployeeNumber());
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }
}
