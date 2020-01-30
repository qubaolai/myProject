package com.qubaolai.service;

import com.github.pagehelper.PageInfo;
import com.qubaolai.common.basic.BaseService;
import com.qubaolai.po.Employee;
import com.qubaolai.vo.ResultVo;

import java.math.BigDecimal;
import java.util.List;
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
     * 通过条件获取员工列表
     * @param param,paginationParam
     * @return
     */
    public PageInfo getEmployeeByConditions(Map<String, Object> param);

    /**
     * 异步查询员工编号是否可用
     * @param empNum
     * @return 可用编号
     */
    public String checkEmpNum(String empNum);
    /**
     * 添加员工 用于管理员添加新员工
     * @param employees
     */
    public void insertEmployee(List<Employee> employees);

    /**
     * 通过姓名查询员工信息
     * @param name
     * @return
     */
    public Employee getEmpByName(String name);

    /**
     * 获取所有员工
     * @return
     */
    public List<Employee> getEmps();
}
