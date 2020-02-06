package com.qubaolai.pojo;

import com.qubaolai.po.Department;
import com.qubaolai.po.Employee;
import lombok.Data;

/**
 * @Description qubaolai
 * @Date 2020/2/6 23:10
 * @Description 签到类父类
 */
@Data
public class AttendanceFather {
    private Employee employee;
    private Department department;
}
