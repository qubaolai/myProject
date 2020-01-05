package com.qubaolai.pojo;

import com.qubaolai.po.Department;
import com.qubaolai.po.Employee;
import com.qubaolai.po.Position;
import lombok.Data;

import javax.annotation.Resource;

/**
 * @Description qubaolai
 * @Date 2020/1/4 17:43
 * @Description 员工类父类, 用于拓展员工类
 */
@Data
public class EmployeeFather {
    @Resource
    private Department department;
    @Resource
    private Position position;
    @Resource
    private Employee employee;
}
