package com.qubaolai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qubaolai.common.basic.impl.BaseServiceImpl;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.LoginException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.MD5Tools;
import com.qubaolai.common.utils.PasswordCheckUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.LogsMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.mapper.myMapper.MyEmployeeMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author qubaolai
 * @date 2019/10/30 14:13
 */
@Log4j
@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private MyEmployeeMapper myEmployeeMapper;
    @Resource
    private LogsMapper logsMapper;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PositionMapper positionMapper;

    @Override
    public void updateEmployee(Employee employee) {
        if (employee != null) {
            employeeMapper.updateByPrimaryKeySelective(employee);
        }
    }

    @Override
    public ResultVo login(Employee employee) {
        //查询用户
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        List<Employee> employees = employeeMapper.selectByExample(example);
        //判空
        if (employees == null || employees.size() <= 0) {
            throw new NoDataException(400, "用户不存在！");
        }
        //获取当前时间的当前天的零点
        Date date = DateUtil.setStartDay(new Date());
        //查询密码错误次数
        LogsExample example1 = new LogsExample();
        LogsExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andEmployeeIdEqualTo(employees.get(0).getId());
        criteria1.andCreatetimeBetween(date, new Date());
        List<Logs> logs1 = logsMapper.selectByExample(example1);
        if (logs1 != null && logs1.size() >= 5) {
            throw new LoginException(208, ErrorEmnus.getMsg(208));
        }
        //判断用户名密码
        if (!MD5Tools.string2MD5(employee.getPassword()).equals(employees.get(0).getPassword())) {
            //用户名密码输入错误，并且错误次数小于5次，在log表中插入记录
            Logs logs = new Logs();
            logs.setId(UUIDUtil.getUUID());
            logs.setEmployeeId(employees.get(0).getId());
            logs.setLog("密码错误");
            logs.setCreatetime(new Date());
            logs.setUpdatetime(new Date());
            logsMapper.insert(logs);
            if (logs1.size() == 3) {
                throw new LoginException(199, "密码已错误四次");
            }
            //用户名或密码错误
            return ResultVo.sendResult(204, ErrorEmnus.getMsg(204));
        }
        return ResultVo.sendResult(200, "success", employees.get(0));
    }

    @Override
    public ResultVo updatePassword(Map<String, String> map) {
        //获取当前登录用户
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        //判空
        String oldPassword = map.get("oldPassword");
        if (StringUtils.isBlank(oldPassword)) {
            throw new ParamException(209, ErrorEmnus.getMsg(209));
        }
        String newPassword = map.get("newPassword");
        if (StringUtils.isBlank(newPassword)) {
            throw new ParamException(209, ErrorEmnus.getMsg(209));
        }
        String confirmPassword = map.get("confirmPassword");
        if (StringUtils.isBlank(confirmPassword)) {
            throw new ParamException(209, ErrorEmnus.getMsg(209));
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new ParamException(207, ErrorEmnus.getMsg(207));
        }
        //正则表达式：允许数字字母下划线组成，不能少于6个字符
        String check = "^[a-zA-Z0-9]\\w{5}$";
        if (!newPassword.matches(check)) {
            throw new ParamException(209, "密码长度少于6位字符");
        }
        //判断旧密码与新密码是否相同
        if (StringUtils.equals(map.get("newPassword"), map.get("oldPassword"))) {
            return ResultVo.sendResult(205, ErrorEmnus.getMsg(205));
        }
        //使用密码工具类验证密码强度
        String level = PasswordCheckUtil.checkComprehensive(newPassword);
        //修改密码
        currentLoginEmployee.setPassword(MD5Tools.string2MD5(map.get("newPassword")));
        employeeService.updateEmployee(currentLoginEmployee);
        return ResultVo.sendResult(200, "success", level);
    }

    @Override
    public PageInfo getEmployeeByConditions(Map<String, Object> map) {
        Map<String, Object> param = new HashMap<>();
        //条件:员工姓名
        if (null != map.get("empName") && !"".equals((String)map.get("empName"))) {
            param.put("empName", map.get("empName"));
        }
        //员工编号
        if (null != map.get("employeeNumber") && !"".equals((String)map.get("employeeNumber"))) {
            param.put("employeeNumber", map.get("employeeNumber"));
        }
        //入职时间
        if (null != map.get("inTimeStart") && !"".equals((String)map.get("inTimeEnd"))) {
            param.put("inTimeStart", map.get("inTimeStart"));
            param.put("inTimeEnd", map.get("inTimeEnd"));
        }
        //性别
        if (null != map.get("sex") && !"".equals((String)map.get("sex"))) {
            param.put("gender", map.get("sex"));
        }
        //学历
        if (null != map.get("education") && !"".equals((String)map.get("education"))) {
            param.put("education", map.get("education"));
        }
        //领导姓名
        AtomicReference<List<Employee>> employees = new AtomicReference<>();
        if (null != map.get("mangerName")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
            String mangerName = (String) map.get("mangerName");
            criteria1.andNameLike("%" + mangerName + "%");
            employees.set(employeeMapper.selectByExample(employeeExample));
        }
        List<String> midList = new ArrayList<>();
        if (null != employees) {
            if (null != employees.get() && 0 < employees.get().size()) {
                for (Employee employee : employees.get()) {
                    midList.add(employee.getId());
                }
            }
            if (null != midList && 0 < midList.size()) {
                param.put("midList", midList);
            }
        }
        //部门名称
        AtomicReference<List<Department>> departments = new AtomicReference<>();
        if (null != map.get("departmentName")) {
            DepartmentExample departmentExample = new DepartmentExample();
            DepartmentExample.Criteria criteria1 = departmentExample.createCriteria();
            String departmentName = (String) map.get("departmentName");
            criteria1.andNameLike("%" + departmentName + "%");
            departments.set(departmentMapper.selectByExample(departmentExample));
        }
        List<Integer> didList = new ArrayList<>();
        if (null != departments) {
            if (null != departments.get() && 0 < departments.get().size()) {
                for (Department department : departments.get()) {
                    didList.add(department.getDepartmentNumber());
                }
            }
            if (null != didList && 0 < didList.size()) {
                param.put("didList", didList);
            }
        }
        //职称
        AtomicReference<List<Position>> positions = new AtomicReference<>();
        if (null != map.get("positionName")) {
            PositionExample positionExample = new PositionExample();
            PositionExample.Criteria criteria1 = positionExample.createCriteria();
            String positionName = (String) map.get("positionName");
            criteria1.andNameEqualTo(positionName);
            positions.set(positionMapper.selectByExample(positionExample));
        }
        List<Integer> pidList = new ArrayList<>();
        if (null != positions) {
            if (null != positions.get() && 0 < positions.get().size()) {
                for (Position position : positions.get()) {
                    pidList.add(position.getPositionNumber());
                }
            }
            if (null != pidList && 0 < pidList.size()) {
                param.put("pidList",pidList);
            }
        }
        Integer pageNo = null;
        Integer pageSize = null;
        if(null != map.get("pageNo") && null != map.get("pageSize")){
            pageNo = (Integer)map.get("pageNo");
            pageSize = (Integer)map.get("pageSize");
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Employee> employeeList = myEmployeeMapper.getEmployeeByConditions(param);
        if (null == employeeList || 0 > employeeList.size()) {
            throw new NoDataException("数据为空!");
        }
        PageInfo page = new PageInfo(employeeList);
        return page;
    }
}
