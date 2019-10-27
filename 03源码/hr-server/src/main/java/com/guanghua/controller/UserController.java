package com.guanghua.controller;

import com.guanghua.common.enums.ErrorEmnus;
import com.guanghua.po.Employee;
import com.guanghua.service.EmployeeService;
import com.guanghua.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private EmployeeService employeeService;
    @RequestMapping("/login")
    public ResultVo login(String id){
        Employee employee = employeeService.selectEmployeeById(id);
        if(employee == null){
            return ResultVo.sendResult(400, ErrorEmnus.getMsg(400));
        }

        return ResultVo.sendResult(200,"success");
    }
}
