package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.DataException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.UUIDUtil;
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
     *
     * @return
     */
    @Override
    public List<Department> getDepts() {
        DepartmentExample example = new DepartmentExample();
        List<Department> departments = departmentMapper.selectByExample(example);
        if (null == departments || 0 >= departments.size()) {
            throw new NoDataException(400, "数据为空");
        }
        return departments;
    }

    /**
     * 根据条件获取部门
     *
     * @param param
     * @return
     */
    @Override
    public List<Department> getDeptsByConditions(Map<String, Object> param) {
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        //部门名称
        if (null != param.get("departmentName") && !"".equals((String) param.get("departmentName"))) {
            String departmentName = (String) param.get("departmentName");
            criteria.andNameLike("%" + departmentName + "%");
        }
        //部门电话
        if (null != param.get("departmentTel") && !"".equals((String) param.get("departmentTel"))) {
            String departmentTel = (String) param.get("departmentTel");
            criteria.andTelephoneLike("%" + departmentTel + "%");
        }
        //领导姓名
        List<Employee> employeeList = null;
        if (null != param.get("manageName") && !"".equals((String) param.get("manageName"))) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria empCriteria = employeeExample.createCriteria();
            String manageName = (String) param.get("manageName");
            empCriteria.andNameEqualTo(manageName);
            employeeList = employeeMapper.selectByExample(employeeExample);
            if (null == employeeList) {
                //查询不到该员工
                throw new NoDataException(400, "查询不到该员工");
            }
        }
        List<String> manageNum = new ArrayList<>();
        for (Employee employee : employeeList) {
            if ("0".equals(employee.getDeviceid())) {
                manageNum.add(employee.getId());
            }
        }
        if (null == manageNum || 0 >= manageNum.size()) {
            throw new NoDataException(400, "部门领导为空");
        }
        if (1 < manageNum.size()) {
            throw new DataException(502, "数据异常!");
        }
        criteria.andManagerEqualTo(manageNum.get(0));
        List<Department> departments = departmentMapper.selectByExample(departmentExample);
        if (null == departments || 0 >= departments.size()) {
            throw new NoDataException(400, "数据为空");
        }
        for (Department department : departments) {
            //查询每个部门管理者的姓名
            EmployeeExample employeeExample1 = new EmployeeExample();
            EmployeeExample.Criteria criteria1 = employeeExample1.createCriteria();
            criteria1.andIdEqualTo(department.getManager());
            List<Employee> employees = employeeMapper.selectByExample(employeeExample1);
            if (null == employees || 0 >= employees.size()) {
                throw new NoDataException(400, "部门领导为空");
            }
            department.setEmployee(employees.get(0));
        }
        return departments;
    }

    @Override
    public Department getDeptByName(String name) {
        if ("".equals(name) || null == name) {
            throw new ParamException(501, "参数异常");
        }
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Department> departments = departmentMapper.selectByExample(example);
        if (null == departments || 0 >= departments.size()) {
            throw new NoDataException(400, "部门不存在");
        }
        return departments.get(0);
    }

    @Override
    public void updateDept(Department department) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        if (null == department.getId() || "".equals(department.getId())) {
            throw new ParamException(501, "参数异常");
        }
        criteria.andIdEqualTo(department.getId());
        departmentMapper.updateByExampleSelective(department, example);
    }

    @Override
    public void insertdept(Map<String, Object> param) {
        Department department = new Department();
        department.setId(UUIDUtil.getUUID());
        if (null == param.get("deptName") || "".equals((String) param.get("deptName"))) {
            throw new ParamException(501, "参数异常");
        }

        department.setName((String) param.get("deptName"));
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria dcriteria = departmentExample.createCriteria();
        dcriteria.andNameEqualTo((String) param.get("deptName"));
        List<Department> dept = departmentMapper.selectByExample(departmentExample);
        if (null != dept && 0 < dept.size()) {
            throw new DataException(201, "部门已存在!");
        }
        if (null == param.get("deptTel") || "".equals((String) param.get("deptTel"))) {
            throw new ParamException(501, "参数异常");
        }
        DepartmentExample departmentExample1 = new DepartmentExample();
        DepartmentExample.Criteria criteria1 = departmentExample1.createCriteria();
        criteria1.andTelephoneEqualTo((String) param.get("deptTel"));
        List<Department> departments = departmentMapper.selectByExample(departmentExample1);
        if(null != departments && 0 < departments.size()){
            throw new ParamException(501, "部门电话重复");
        }
        department.setTelephone((String) param.get("deptTel"));
        if (null == param.get("manageName") || "".equals((String) param.get("manageName"))) {
            throw new ParamException(501, "参数异常");
        }
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //使用员工编号 避免因为重名员工出现异常
        criteria.andUsernameEqualTo((String) param.get("manageName"));
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (null == employees || 0 >= employees.size()) {
            throw new NoDataException(400, "员工不存在");
        }
        department.setManager(employees.get(0).getId());
        if (null != param.get("notes") || !"".equals((String) param.get("notes"))) {
            department.setNotes((String) param.get("notes"));
        }
        departmentMapper.insert(department);
    }
}
