package com.qubaolai.service;

import com.qubaolai.common.basic.BaseService;
import com.qubaolai.po.Employee;
import com.qubaolai.vo.ResultVo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/30 14:03
 */
public interface EmployeeService extends BaseService {
    /**
     * 用户登录
     * @param employee
     * @return
     */
    public ResultVo login(Employee employee);

    /**
     * 用户密码修改
     * @param map
     * @return
     */
    public ResultVo updatePassword(Map<String,String> map);

    /**
     * 用户信息修改
     * @param employee
     */
    public void updateEmployee(Employee employee);

    /**
     * 员工上下班签到
     * @param flag 签到标志(上班或者下班)
     */
    public ResultVo workSingIn(String flag);
}
