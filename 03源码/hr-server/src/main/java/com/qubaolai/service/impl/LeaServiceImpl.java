package com.qubaolai.service.impl;

import com.qubaolai.common.basic.BaseService;
import com.qubaolai.common.exception.exceptions.DataException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.StringUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.LeaMapper;
import com.qubaolai.mapper.myMapper.MyLeaMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.service.LeaService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeaServiceImpl
 * @Author qubaolai
 * @Date 2020/01/19 9:39
 * @Description 假期相关业务实现类
 */
@Service
public class LeaServiceImpl implements LeaService {

    @Resource
    private LeaMapper leaMapper;
    @Resource
    private MyLeaMapper myLeaMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private EmployeeService employeeService;

    @Override
    public ResultVo applyHoilday(Map<String, Object> param) {
        //通过用户名和假期开始和结束时间查询记录
        Map<String, Object> params = new HashMap<>();
        if (null == param.get("startTime") || "".equals(param.get("startTime"))) {
            throw new ParamException(501, "参数异常");
        }
        params.put("startTime", (String) param.get("startTime"));
        if (null == param.get("endTime") || "".equals(param.get("endTime"))) {
            throw new ParamException(501, "参数异常");
        }
        params.put("endTime", (String) param.get("endTime"));
        //如果假期开始时间或者结束时间或者在这个时间段内
        //获取当前登录用户
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        params.put("empNum", currentLoginEmployee.getId());
        Integer leas = myLeaMapper.getLeasByConditions(params);
        if (0 < leas) {
            throw new DataException(201, "数据已存在");
        }
        Lea lea = new Lea();
        lea.setId(UUIDUtil.getUUID());
        //设置请假记录的员工id
        lea.setEmployeeNumber(currentLoginEmployee.getId());
        //设置请假记录部门id
        lea.setDepartmentNumber(currentLoginEmployee.getDepartmentNumber());
        //如果请假人为部门领导设置请假人所在部门领导id
        if ("0".equals(currentLoginEmployee.getDeviceid())) {
            //请假人为部门领导
            lea.setManager(currentLoginEmployee.getId());
        } else {
            //查询该部门领导
            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andDepartmentNumberEqualTo(currentLoginEmployee.getDepartmentNumber());
            criteria.andDeviceidEqualTo("0");
            List<Employee> employees = employeeMapper.selectByExample(example);
            //请假人为部门普通员工,设置部门领导id
            lea.setManager(employees.get(0).getId());
        }
        //设置请假开始时间
        lea.setStartTime((String) param.get("startTime"));
        //设置请假结束时间
        lea.setEndTime((String) param.get("endTime"));
        //设置请假原因
        if (null == param.get("reason") || "".equals(param.get("reason"))) {
            throw new ParamException(501, "请输入请假原因!");
        }
        //判断原因长度
        if (200 <= StringUtil.length((String) param.get("reason"))) {
            throw new ParamException(502, "长度超出");
        }
        lea.setReason((String) param.get("reason"));
        //设置请假类型
        int type = Integer.parseInt((String) param.get("type"));
        lea.setType(type);
        //审批状态 默认为未审批
        lea.setStatus(0);
        //数据落库
        leaMapper.insertSelective(lea);
        return ResultVo.sendResult(200, "success");
    }

    @Override
    public ResultVo getLeasByConditions(Map<String, Object> param) {
        LeaExample example = new LeaExample();
        LeaExample.Criteria criteria = example.createCriteria();
        //员工姓名模糊查询
        if (null != param.get("empName") && !"".equals((String) param.get("empName"))) {
            Employee employee = employeeService.getEmpByName((String) param.get("empName"));
            criteria.andEmployeeNumberLike("%" + employee.getId() + "%");
        }
        //员工编号模糊查询
        if (null != param.get("empNum") && !"".equals((String) param.get("empNum"))) {
            EmployeeExample example1 = new EmployeeExample();
            EmployeeExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUsernameLike("%" + (String) param.get("empNum") + "%");
            List<Employee> employees = employeeMapper.selectByExample(example1);
            List<String> empNums = new ArrayList<>();
            for (Employee employee : employees) {
                empNums.add(employee.getId());
            }
            if (null != empNums && 0 < empNums.size()) {
                criteria.andEmployeeNumberIn(empNums);
            }
        }
        //部门精确查询
        if (null != param.get("deptNum") && !"".equals((String) param.get("deptNum"))) {
            criteria.andDepartmentNumberEqualTo((String) param.get("deptNum"));
        }
        //时间区间查询(包括开始时间,结束时间)
        //假期开始时间的开始和结束的区间
        if (null != param.get("startTimeIntervalStart") && !"".equals((String) param.get("startTimeIntervalEnd"))) {
            String startTime = (String) param.get("startTimeIntervalStart");
            String endTime = (String) param.get("startTimeIntervalEnd");
            criteria.andStartTimeBetween(startTime, endTime);
        }
        //假期结束时间的开始和结束的区间
        if (null != param.get("endTimeIntervalStart") && !"".equals((String) param.get("endTimeIntervalEnd"))) {
            String startTime = (String) param.get("endTimeIntervalStart");
            String endTime = (String) param.get("endTimeIntervalEnd");
            criteria.andEndTimeBetween(startTime, endTime);
        }
        //审批状态查询
        if (null != param.get("status") && !"".equals((String) param.get("status"))) {
            criteria.andStatusEqualTo(Integer.parseInt((String) param.get("status")));
        }
        List<Lea> leas = leaMapper.selectByExample(example);
        if (null == leas && 0 >= leas.size()) {
            throw new NoDataException(400, "数据为空");
        }
        for(Lea lea : leas){
            Employee employee = employeeMapper.selectByPrimaryKey(lea.getEmployeeNumber());
            lea.setEmpName(employee.getName());
            lea.setEmpNum(employee.getUsername());
            Department department = departmentMapper.selectByPrimaryKey(lea.getDepartmentNumber());
            lea.setDeptName(department.getName());
        }
        return ResultVo.sendResult(200, "success", leas);
    }

    @Override
    public void approvalHoliday(Map<String, Object> param) {
        if(null == param.get("leaId") || "".equals((String)param.get("leaId"))){
            throw new ParamException(501, "参数异常");
        }
        Lea lea = leaMapper.selectByPrimaryKey((String) param.get("leaId"));
        if(null == lea){
            throw new NoDataException(400, "数据为空");
        }
        //未审批
        if(lea.getStatus() == 0){
            if(null == param.get("status")){
                throw new ParamException(501, "参数异常");
            }
            lea.setStatus((Integer)param.get("status"));
            if(2 == (Integer)param.get("status")){
                if(null == param.get("notes") || "".equals((String)param.get("notes"))){
                    throw new ParamException(501, "请输入审批意见");
                }
                lea.setNotes((String)param.get("notes"));
            }
        }else{
            throw new DataException(502, "该记录已被审批");
        }
        leaMapper.updateByPrimaryKey(lea);
    }

}