package com.guanghua.controller;

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
    public ResultVo login(){
        return ResultVo.sendResult(200,"success",employeeService.selectEmployeeById("asd"));
    }
}
