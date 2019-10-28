package com.guanghua.controller;

import com.guanghua.common.exception.exceptions.ParamException;
import com.guanghua.common.exception.exceptions.UserException;
import com.guanghua.po.Employee;
import com.guanghua.service.EmployeeService;
import com.guanghua.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private EmployeeService employeeService;
    @PostMapping("/login")
    public ResultVo login(Employee employee){
        if(StringUtils.isEmpty(employee.getEmployeeNumber())){
            throw new ParamException("用户名为空！");
        }
        if(StringUtils.isEmpty(employee.getPassword())){
            throw new UserException("密码为空！");
        }
        ResultVo resultVo = employeeService.login(employee);
        return resultVo;
    }

}
