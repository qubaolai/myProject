package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.DataException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Department;
import com.qubaolai.po.DepartmentExample;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:31
 * @Description 部门业务实现类
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getDepts() {
        DepartmentExample example = new DepartmentExample();
        List<Department> departments = departmentMapper.selectByExample(example);
        if(null == departments || 0 >= departments.size()){
            throw new NoDataException(400, "数据为空");
        }
        return departments;
    }

    /**
     * 根据条件获取部门
     * @param param
     * @return
     */
    @Override
    public List<Department> getDeptsByConditions(Map<String, Object> param) {
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        //部门名称
        if(null == param.get("departmentName")){
            throw new ParamException(501, "参数异常");
        }
        String departmentName = (String)param.get("departmentName");
        criteria.andNameLike("%" + departmentName + "%");
        //部门电话
        if(null == param.get("departmentTel")){
            throw new ParamException(501, "参数异常");
        }
        String departmentTel = (String)param.get("departmentTel");
        criteria.andTelephoneLike("%" + departmentTel +"%");
        //领导姓名
        if(null == param.get("manageName")){
            throw new ParamException(501, "参数异常");
        }
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria empCriteria = employeeExample.createCriteria();
        String manageName = (String)param.get("manageName");
        empCriteria.andNameEqualTo(manageName);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if(null == employeeList){
            //查询不到该员工
            throw new NoDataException(400, "查询不到该员工");
        }
        List<String> manageNum = new ArrayList<>();
        for(Employee employee : employeeList){
            if("0".equals(employee.getDeviceid())){
                manageNum.add(employee.getId());
            }
        }
        if(null == manageNum || 0 >= manageNum.size()){
            throw new NoDataException(400, "部门领导为空");
        }
        if(1 < manageNum.size()){
            throw new DataException(502, "数据异常!");
        }
        criteria.andManagerEqualTo(manageNum.get(0));
        List<Department> departments = departmentMapper.selectByExample(departmentExample);
        if(null == departments || 0 >= departments.size()){
            throw new NoDataException(400, "数据为空");
        }
        for(Department department : departments){
            //查询每个部门管理者的姓名
            EmployeeExample employeeExample1 = new EmployeeExample();
            EmployeeExample.Criteria criteria1 = employeeExample1.createCriteria();
            criteria1.andIdEqualTo(department.getManager());
            List<Employee> employees = employeeMapper.selectByExample(employeeExample1);
            if(null == employees || 0 >= employees.size()){
                throw new NoDataException(400, "部门领导为空");
            }
            department.setEmployee(employees.get(0));
        }
        return departments;
    }

    @Override
    public Department getDeptByName(String name) {
        if("".equals(name) || null == name){
            throw new ParamException(501, "参数异常");
        }
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Department> departments = departmentMapper.selectByExample(example);
        if(null == departments || 0 >= departments.size()){
            throw new NoDataException(400, "部门不存在");
        }
        return departments.get(0);
    }

    @Override
    public void updateDept(Department department) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        if(null == department.getId() || "".equals(department.getId())){
            throw new ParamException(501, "参数异常");
        }
        criteria.andIdEqualTo(department.getId());
        departmentMapper.updateByExampleSelective(department, example);
    }
}
