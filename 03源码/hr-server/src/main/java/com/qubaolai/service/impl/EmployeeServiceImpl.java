package com.qubaolai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qubaolai.common.basic.impl.BaseServiceImpl;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.DataException;
import com.qubaolai.common.exception.exceptions.LoginException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.MD5Tools;
import com.qubaolai.common.utils.PasswordCheckUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.*;
import com.qubaolai.mapper.myMapper.MyEmployeeMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

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
    private DepartmentService departmentService;
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private MoveMapper moveMapper;

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
        if (!MD5Tools.string2MD5(oldPassword).equals(currentLoginEmployee.getPassword())) {
            throw new DataException(502, "旧密码错误!");
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
            throw new ParamException(209, "密码格式不符");
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
        if (null != map.get("empName") && !"".equals((String) map.get("empName"))) {
            param.put("empName", map.get("empName"));
        }
        //员工编号
        if (null != map.get("employeeNumber") && !"".equals((String) map.get("employeeNumber"))) {
            param.put("employeeNumber", map.get("employeeNumber"));
        }
        //入职时间
        if (null != map.get("inTimeStart") && !"".equals((String) map.get("inTimeEnd"))) {
            param.put("inTimeStart", map.get("inTimeStart"));
            param.put("inTimeEnd", map.get("inTimeEnd"));
        }
        //性别
        if (null != map.get("sex") && !"".equals((String) map.get("sex"))) {
            param.put("gender", map.get("sex"));
        }
        //学历
        if (null != map.get("education") && !"".equals((String) map.get("education"))) {
            param.put("education", map.get("education"));
        }
        //领导姓名
        AtomicReference<List<Employee>> employees = new AtomicReference<>();
        if (null != map.get("mangerName") && !"".equals((String) map.get("mangerName"))) {
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
        //部门
        if (null != map.get("departmentNumber") && !"".equals((String) map.get("departmentNumber"))) {
            param.put("did", (String) map.get("departmentNumber"));
        }
        //职称
        if (null != map.get("positionNumber") && !"".equals((String) map.get("positionNumber"))) {
            param.put("pid", (String) map.get("positionNumber"));
        }
        Integer pageNo = null;
        Integer pageSize = null;
        if (null != map.get("pageNo") && null != map.get("pageSize")) {
            pageNo = (Integer) map.get("pageNo");
            pageSize = (Integer) map.get("pageSize");
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Employee> employeeList = myEmployeeMapper.getEmployeeByConditions(param);
        if (null == employeeList || 0 > employeeList.size()) {
            throw new NoDataException("数据为空!");
        }
        PageInfo page = new PageInfo(employeeList);
        return page;
    }

    @Override
    public void checkEmpNum(String empNum) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        if (null == empNum || "".equals(empNum)) {
            throw new ParamException(500, "参数异常");
        }
        criteria.andUsernameEqualTo(empNum);
        example.setOrderByClause("username");
        List<Employee> employeeList = employeeMapper.selectByExample(example);
        if(employeeList!=null&&employeeList.size()>0){
            throw new DataException(208, "员工编号存在!");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertEmployee(List<Employee> employees) {
        for (Employee employee : employees) {
            //判断用户名是否存在
            employee.setUsername(employee.getUsername());
            employee.setId(UUIDUtil.getUUID());
            String MD5Password = MD5Tools.string2MD5("admin123");
            employee.setPassword(MD5Password);
            employee.setRole(1);
            //根据员工的部门id查询该部门领导
            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            if (null == employee.getDepartmentNumber() || "0".equals(employee.getDepartmentNumber())) {
                throw new ParamException(500, "参数异常!");
            }
            criteria.andDepartmentNumberEqualTo(employee.getDepartmentNumber());
            criteria.andDeviceidEqualTo("0");
            List<Employee> employeeList1 = employeeMapper.selectByExample(example);
            if (null == employeeList1 || 0 >= employeeList1.size()) {
                //添加的员工为部门领导
                employee.setDeviceid("0");
                employee.setManageerId(employee.getId());
                //修改部门信息表
                Department department = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
                department.setManager(employee.getId());
                departmentMapper.updateByPrimaryKey(department);
            } else {
                employee.setManageerId(employeeList1.get(0).getId());
            }
            employee.setInTime(DateUtil.getDate());
            employee.setEmail(employee.getUsername() + "@hr.com");
            employeeMapper.insert(employee);
        }
    }

    @Override
    public Employee getEmpByName(String name) {
        if ("".equals(name) || null == name) {
            throw new ParamException(501, "参数异常");
        }
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (null == employees || 0 >= employees.size()) {
            throw new NoDataException(400, "数据为空");
        }
        return employees.get(0);
    }

    @Override
    public List<Employee> getEmps() {
        EmployeeExample employeeExample = new EmployeeExample();
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        return employeeList;
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        //向离职表中插入记录
        History history = new History();
        history.setId(UUIDUtil.getUUID());
        history.setName(employee.getName());
        history.setEmployeeNumber(employee.getId());
        history.setGender(employee.getGender());
        history.setBirthday(employee.getBirthday());
        history.setTelephone(employee.getTelephone());
        history.setEmail(employee.getEmail());
        history.setAddress(employee.getAddress());
        history.setEducation(employee.getEducation());
        history.setInTime(employee.getInTime());
        history.setOutTime(DateUtil.getDate());
        history.setDepartmentNumber(employee.getDepartmentNumber());
        history.setPositionNumber(employee.getPositionNumber());
        historyMapper.insert(history);
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void schedulingEmployee(Map<String, Object> param) {
        //向move插入数据
        if(null == param.get("empNum") || "".equals((String)param.get("empNum"))){
            throw new ParamException(501, "获取员工编号失败!");
        }
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo((String) param.get("empNum"));
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if(employeeList.size() > 1){
            throw new DataException(502, "数据异常");
        }
        Employee employee = employeeList.get(0);
        if(employeeList == null || employeeList.size() <= 0){
            throw new NoDataException(400, "该员工不存在!");
        }
        if(null == param.get("type") || "".equals((String)param.get("type"))){
            throw new ParamException(501, "获取调动类型失败!");
        }
        if(null == param.get("deptNum") || "".equals((String)param.get("deptNum"))){
            throw new ParamException(501, "获取部门编号失败!");
        }
        if(null == param.get("position") || "".equals((String)param.get("position"))){
            throw new ParamException(501, "获取职位编号失败!");
        }
        //部门调动
        if("0".equals((String)param.get("type"))){
            Move move = new Move();
            move.setId(UUIDUtil.getUUID());
            move.setEmployeeNumber((String)param.get("empNum"));
            //判断被调动员工时不是领导 是领导 则将该员工的调动前部门manageer值为空 修改员工将vaild为空领导id为调动后的部门的manager
            //调动后部门
            Department department1 = departmentMapper.selectByPrimaryKey((String) param.get("deptNum"));
            //调动前部门
            Department department = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
            if(employee.getDeviceid().equals("0")){
                //领导
                // 修改调动前部门信息
                department.setManager(null);
                departmentMapper.updateByPrimaryKey(department);
            }
            //插入调动记录\
            //调动前领导
            move.setManagerId(employee.getManageerId());
            move.setUpdateTime(DateUtil.getDate());
            move.setMoveBefore(department.getId());
            move.setMoveAfter(department1.getId());
            move.setMoveType(0);
            moveMapper.insert(move);
            //如果职位变动
            if(!employee.getPositionNumber().equals((String)param.get("position"))){
                Move move1 = new Move();
                move1.setId(UUIDUtil.getUUID());
                move1.setMoveType(2);
                move1.setEmployeeNumber((String)param.get("empNum"));
                move1.setMoveBefore(employee.getPositionNumber());
                move1.setMoveAfter((String)param.get("position"));
                move1.setUpdateTime(DateUtil.getDate());
                move1.setManagerId(employee.getManageerId());
                moveMapper.insert(move1);
                //修改员工职位调动
                employee.setPositionNumber((String)param.get("position"));
            }
            //修改员工信息
            employee.setDeviceid(null);
            employee.setDepartmentNumber(department1.getId());
            employee.setManageerId(department1.getManager());
            employeeMapper.updateByPrimaryKey(employee);
            //该部门所有员工的领导id设置为空
        }
        //职位调动
        if("1".equals((String)param.get("type"))){
            Move move = new Move();
            move.setId(UUIDUtil.getUUID());
            move.setMoveType(1);
            move.setEmployeeNumber((String)param.get("empNum"));
            move.setMoveBefore(employee.getPositionNumber());
            move.setMoveAfter((String)param.get("position"));
            move.setUpdateTime(DateUtil.getDate());
            move.setManagerId(employee.getManageerId());
            moveMapper.insert(move);
            employee.setPositionNumber((String)param.get("position"));
            employeeMapper.updateByPrimaryKey(employee);
        }
    }

    @Override
    public Employee getEmployee(String id) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo(id);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if(employeeList == null || employeeList.size() <= 0){
            throw new NoDataException(400, "员工不存在!");
        }
        if(employeeList.size() > 1){
            throw new DataException(502, "数据异常");
        }
        return employeeList.get(0);
    }
}
