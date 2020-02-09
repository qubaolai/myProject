package com.qubaolai.controller;

import com.github.pagehelper.PageInfo;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.Move;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.service.MoveService;
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
    private MoveService moveService;

    /**
     * 用户登录
     *
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody Employee employee) {
        //判断传入用户是否为空
        if (employee == null) {
            throw new ParamException("参数信息为空！");
        }
        ResultVo resultVo = employeeService.login(employee);
        Employee employee1 = new Employee();
        if (resultVo.getCode() == 200) {
            employee1 = (Employee) resultVo.getData();
            //生成token
            String token = JWTUtil.encrypt(employee);
            //定义返回数据
            Map<String, Object> map = new HashMap<>();
            map.put("user", employee1);
            map.put("token", token);
            log.info(token);
            return ResultVo.sendResult(200, "success", map);
        } else {
            return resultVo;
        }
    }

    /**
     * 用户密码修改
     *
     * @param map
     * @return
     */
    @PutMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody Map<String, String> map) {
        ResultVo resultVo = employeeService.updatePassword(map);
        return resultVo;
    }

    /**
     * 按条件查询员工信息
     *
     * @param map
     * @return
     */
    @PostMapping("/employeeList")
    public ResultVo getEmployeeList(@RequestBody Map<String, Object> map) {
        if (null != map) {
            PageInfo pageInfo = employeeService.getEmployeeByConditions(map);
            if (null != pageInfo.getList() && 0 < pageInfo.getList().size()) {
                return ResultVo.sendResult(200, "success", pageInfo);
            }
        }
        return ResultVo.sendResult(400, "NoData");
    }

    /**
     * 检查用户名是否可用
     *
     * @param empNum
     * @return
     */
    @GetMapping("/checkEmployeeNumber")
    public ResultVo checkEmpNum(String empNum) {
        if (null == empNum || "".equals(empNum)) {
            throw new ParamException(500, "参数异常");
        }
        employeeService.checkEmpNum(empNum);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 添加员工
     *
     * @param map
     * @return
     */
    @PostMapping("/insertEmp")
    public ResultVo insertEmp(@RequestBody Map<String, List<Employee>> map) {
        if (null == map.get("employees") || map.get("employees") instanceof Employee) {
            throw new ParamException(500, "参数异常!");
        }
        List<Employee> employeesList = map.get("employees");
        employeeService.insertEmployee(employeesList);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 获取所有员工
     *
     * @return
     */
    @GetMapping("/getEmpList")
    public ResultVo getEmps() {
        List<Employee> emps = employeeService.getEmps();
        return ResultVo.sendResult(200, "success", emps);
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ResultVo logout(HttpServletRequest request) {
        //从session中删除当前登录用户
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //token缓存清空

        return ResultVo.sendResult(200, "success");
    }

    /**
     * 修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping("/updateEmployee")
    public ResultVo updateEmployee(@RequestBody Employee employee) {
        if (employee == null) {
            throw new ParamException(501, "参数异常");
        }
        employeeService.updateEmployee(employee);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除员工信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteEmployee")
    public ResultVo deleteEmployee(String id) {
        if (null == id) {
            throw new ParamException(501, "参数异常");
        }
        employeeService.deleteEmployee(id);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 通过员工编号查询员工
     *
     * @param id
     * @return
     */
    @GetMapping("/getEmployee")
    public ResultVo getEmployee(String id) {
        if (null == id || "".equals(id)) {
            throw new ParamException(501, "参数异常");
        }
        Employee employee = employeeService.getEmployee(id);
        return ResultVo.sendResult(200, "success", employee);
    }

    /**
     * 员工调度
     *
     * @param param
     * @return
     */
    @PutMapping("/schedulingUser")
    public ResultVo schedulingUser(@RequestBody Map<String, Object> param) {
        if (null == param) {
            throw new ParamException("参数异常");
        }
        employeeService.schedulingEmployee(param);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 获取员工调度记录
     * @param param
     * @return
     */
    @PostMapping("/getMoveLog")
    public ResultVo getMoveLog(@RequestBody Map<String, Object> param) {
        if (param == null) {
            throw new ParamException(502, "参数异常!");
        }
        List<Move> moveLog = moveService.getMoveLog(param);
        return ResultVo.sendResult(200, "success", moveLog);
    }
}
