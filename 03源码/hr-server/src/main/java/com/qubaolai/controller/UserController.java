package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.po.Employee;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author qubaolai
 * @date 2019/10/30 14:25
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private EmployeeService employeeService;
    @PostMapping("login")
    public ResultVo login(@RequestBody Employee employee){
        //判断传入用户是否为空
        if(employee == null){
            throw new ParamException("参数信息为空！");
        }
        ResultVo resultVo = employeeService.login(employee);
        return ResultVo.sendResult(200,"success",resultVo);
    }
    @GetMapping("/test")
    public ResultVo test(){
        return ResultVo.sendResult(200,"ok","haode");
    }
}
