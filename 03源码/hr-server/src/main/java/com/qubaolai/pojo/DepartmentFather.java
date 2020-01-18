package com.qubaolai.pojo;

import com.qubaolai.po.Employee;
import lombok.Data;

import javax.annotation.Resource;

/**
 * @Description qubaolai
 * @Date 2020/1/18 15:21
 * @Description 部门类父类
 */
@Data
public class DepartmentFather {
    @Resource
    private Employee employee;
}
