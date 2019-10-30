package com.qubaolai.serve;

import com.qubaolai.po.Employee;

import java.util.List;

/**
 * @author qubaolai
 * @date 2019/10/29 11:49
 */
public interface EmployeeServe {
    public List<Employee> selectEmp(Employee employee);
}
