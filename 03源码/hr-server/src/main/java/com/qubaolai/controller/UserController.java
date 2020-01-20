package com.qubaolai.controller;

import com.github.pagehelper.PageInfo;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 用户登录
     * @param employee
     * @return
     */
    @PostMapping("/login")
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
            log.info(token);
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

    /**
     *  按条件查询员工信息
     * @param map
     * @return
     */
    @PostMapping("/employeeList")
    public ResultVo getEmployeeList(@RequestBody Map<String,Object> map){
        if(null != map){
            PageInfo pageInfo = employeeService.getEmployeeByConditions(map);
            if(null != pageInfo.getList() && 0 < pageInfo.getList().size()){
                return ResultVo.sendResult(200, "success",pageInfo);
            }
        }
        return ResultVo.sendResult(400, "NoData");
    }

    /**
     * 检查用户名是否可用
     * @param empNum
     * @return
     */
    @GetMapping("/checkEmployeeNumber")
    public ResultVo checkEmpNum(String empNum){
        if(null == empNum || "".equals(empNum)){
            throw new ParamException(500, "参数异常");
        }
        String result = employeeService.checkEmpNum(empNum);
        if(null == result){
            return ResultVo.sendResult(200, "success");
        }
        return ResultVo.sendResult(208, "员工编号存在!", result);
    }

    /**
     * 添加员工
     * @param map
     * @return
     */
    @PostMapping("/insertEmp")
    public ResultVo insertEmp(@RequestBody Map<String, List<Employee>> map){
        if(null == map.get("employees") || map.get("employees") instanceof Employee){
            throw new ParamException(500, "参数异常!");
        }
        List<Employee> employeesList = map.get("employees");
        employeeService.insertEmployee(employeesList);
        return ResultVo.sendResult(200, "success");
    }

    @RequestMapping("/logout")
    public ResultVo logout(HttpServletRequest request){
        //从session中删除当前登录用户
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //token缓存清空

        return ResultVo.sendResult(200, "success");
    }
}
