package com.qubaolai.controller;

import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.common.utils.PasswordCheckUtil;
import com.qubaolai.po.Employee;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 用户登录
     * @param employee
     * @return
     */
    @PostMapping("login")
    public ResultVo login(@RequestBody Employee employee, HttpServletRequest request){
        //判断传入用户是否为空
        if(employee == null){
            throw new ParamException("参数信息为空！");
        }
        ResultVo resultVo = employeeService.login(employee);
        Employee employee1 = new Employee();
        if(resultVo.getCode() == 200){
            employee1 = (Employee) resultVo.getData();
            //生成token
            String token = JWTUtil.encrypt(employee);
            //定义返回数据
            Map<String, Object> map = new HashMap<>();
            map.put("user", employee1);
            map.put("token",token);
            return ResultVo.sendResult(200,"success",map);
        }else {
            return resultVo;
        }
    }

    /**
     * 用户密码修改
     * @param map
     * @return
     */
    @PutMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody Map<String,String> map){
        ResultVo resultVo = employeeService.updatePassword(map);
        return resultVo;
    }
}
