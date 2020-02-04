package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.DataException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.MoveMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private MoveMapper moveMapper;

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
        if(null != employeeList && 0 < employeeList.size()){
            for (Employee employee : employeeList) {
                if ("0".equals(employee.getDeviceid())) {
                    manageNum.add(employee.getId());
                }
            }
        }
//        if (null == manageNum || 0 >= manageNum.size()) {
//            throw new NoDataException(400, "部门领导为空");
//        }
        if (1 < manageNum.size()) {
            throw new DataException(502, "数据异常!");
        }
        if (null != manageNum && 0 < manageNum.size()){
            criteria.andManagerEqualTo(manageNum.get(0));
        }
        criteria.andValidEqualTo(0);
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

    @Transactional(propagation= Propagation.REQUIRED)
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
        Employee employee = employeeMapper.selectByPrimaryKey((String) param.get("manageName"));
        if (null == employee) {
            throw new NoDataException(400, "员工不存在");
        }
        //判断员工是否已经为某部门领导
        if(null != employee.getDeviceid() && !"".equals(employee.getDeviceid())){
            throw new DataException(401, "员工已是其他部门领导!");
        }
        //向员工调度表中添加信息
        Move move = new Move();
        move.setId(UUIDUtil.getUUID());
        move.setDeptBefore(employee.getDepartmentNumber());
        move.setManagerId(employee.getManageerId());
        //修改员工部门
        employee.setDeviceid("0");
        employee.setManageerId(employee.getId());
        employee.setDepartmentNumber(department.getId());
        employeeMapper.updateByPrimaryKeySelective(employee);
        move.setDeptAfter(employee.getDepartmentNumber());
        move.setEmployeeNumber(employee.getId());
        move.setUpdateTime(DateUtil.getDate());
        moveMapper.insertSelective(move);
        department.setManager(employee.getId());
        if (null != param.get("notes") || !"".equals((String) param.get("notes"))) {
            department.setNotes((String) param.get("notes"));
        }
        department.setValid(0);
        departmentMapper.insert(department);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void deleteDept(String id) {
        //逻辑删除 暂时不需要关联修改员工信息
        Department department = new Department();
        department.setId(id);
        department.setValid(1);
        //修改员工信息将部门和部门身份设置为空
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andDepartmentNumberEqualTo(id);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if(null == employeeList || 0 >= employeeList.size()){
            throw new NoDataException(400, "数据为空");
        }
        for(Employee employee : employeeList){
            if(employee.getDepartmentNumber().equals(id) && employee.getDeviceid().equals("0")){
                employee.setDeviceid(null);
            }
            if(employee.getDepartmentNumber().equals(id)){
                employee.setDepartmentNumber(null);
                employee.setManageerId(null);
            }
            employeeMapper.updateByExample(employee,employeeExample);
        }
        departmentMapper.updateByPrimaryKeySelective(department);
    }
}
