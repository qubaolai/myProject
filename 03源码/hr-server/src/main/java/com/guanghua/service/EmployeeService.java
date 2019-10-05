package com.guanghua.service;

import com.guanghua.po.Employee;

public interface EmployeeService {
    public Employee selectEmployeeById(String id);
    public void insertEmployeeByemp(Employee employee);
}
