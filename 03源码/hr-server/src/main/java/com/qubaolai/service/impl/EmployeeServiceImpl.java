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
import javax.servlet.http.HttpServletRequest;
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
    private FileInfoMapper fileInfoMapper;
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
        if (employeeList != null && employeeList.size() > 0) {
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
            //用户添加默认头像
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath("D:/img");
            fileInfo.setFileName("userHead.jpg");
            fileInfo.setUploadTime(DateUtil.getDate());
            fileInfo.setEmployeeNumber(employee.getId());
            fileInfo.setId(UUIDUtil.getUUID());
            fileInfoMapper.insert(fileInfo);
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
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andDeviceidIsNull();
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
        String date = DateUtil.convert(new Date());
        //根据员工的编号查询员工
        if (null == param.get("empNum") || "".equals(param.get("empNum"))) {
            throw new ParamException(501, "获取员工编号失败!");
        }
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo((String) param.get("empNum"));
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if (employeeList == null || 0 >= employeeList.size()) {
            throw new ParamException(400, "员工不存在!");
        }
        Employee employee = employeeList.get(0);
        //根据部门id查询调度后的部门
        if (null == param.get("deptNum") || "".equals(param.get("deptNum"))) {
            throw new ParamException(501, "获取部门编号失败!");
        }
        Department afterDept = departmentMapper.selectByPrimaryKey((String) param.get("deptNum"));
        //判断调动类型
        String type = (String) param.get("type");
        if (type == null || "".equals(type)) {
            throw new ParamException(501, "获取调度类型失败!");
        }
        //部门调动
        if (type.equals("0")) {
            //判断调度的员工是否为部门领导
            //是领导
            if (employee.getDeviceid() != null && !"".equals(employee.getDeviceid())) {
                //将员工的领导标志和领导id置空
                employee.setDeviceid(null);
                employee.setManageerId(afterDept.getManager());
                //将员工对应的部门领导id置空
                Department beforeDept = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
                beforeDept.setManager(null);
                departmentMapper.updateByPrimaryKey(beforeDept);
                //将所有领导id为被调度员工id的员工的领导id置空
                EmployeeExample example = new EmployeeExample();
                EmployeeExample.Criteria criteria1 = example.createCriteria();
                criteria1.andManageerIdEqualTo(employee.getId());
                List<Employee> employeeList1 = employeeMapper.selectByExample(example);
                for (Employee employee1 : employeeList1) {
                    employee1.setManageerId(null);
                    employeeMapper.updateByPrimaryKey(employee1);
                }
            } else {
                //不是领导
                //判断调度后的部门是否存在领导
                if (afterDept.getManager() != null && !"".equals(afterDept.getManager())) {
                    //存在: 将被调度的员工的领导id设置为调度后的部门的领导id
                    employee.setManageerId(afterDept.getManager());
                    employee.setDeviceid("0");
                } else {
                    //不存在:将调度后的部门的领导id设置为被调度员工的id
                    afterDept.setManager(employee.getId());
                    departmentMapper.updateByPrimaryKey(afterDept);
                    //修改员工调动后部门员工
                    EmployeeExample employeeExample1 = new EmployeeExample();
                    EmployeeExample.Criteria criteria1 = employeeExample1.createCriteria();
                    criteria1.andDepartmentNumberEqualTo(afterDept.getId());
                    List<Employee> employeeList1 = employeeMapper.selectByExample(employeeExample1);
                    employee.setManageerId(employee.getId());
                    for(Employee employee1 : employeeList1){
                        employee1.setManageerId(employee.getId());
                        employeeMapper.updateByPrimaryKey(employee1);
                    }

                }
            }
            Move move = new Move();
            move.setId(UUIDUtil.getUUID());
            move.setDeptBefore(employee.getDepartmentNumber());
            move.setDeptAfter(afterDept.getId());
            move.setPositionBefore(employee.getPositionNumber());
            //调度前领导
            move.setManagerId(employee.getManageerId());
            //向调度记录表插入数据 调度类型为0:部门调度
            move.setMoveType(0);
            move.setUpdateTime(date);
            move.setEmployeeNumber(employee.getId());
            moveMapper.insert(move);
            //判断员工职位是否发生改变
            if (!employee.getPositionNumber().equals((String) param.get("position"))) {
                //改变: 查询之前的部门调度记录 修改该记录添加职位调度信息
                MoveExample moveExample = new MoveExample();
                MoveExample.Criteria criteria1 = moveExample.createCriteria();
                criteria1.andEmployeeNumberEqualTo(employee.getId());
                criteria1.andDeptAfterEqualTo(afterDept.getId());
                criteria1.andUpdateTimeEqualTo(date);
                List<Move> moves = moveMapper.selectByExample(moveExample);
                if (moves == null || 0 >= moves.size()) {
                    throw new ParamException(400, "获取历史调度记录失败!");
                }
                Move move1 = moves.get(0);
                move1.setPositionBefore(employee.getPositionNumber());
                move1.setPositionAfter((String) param.get("position"));
                //调度类型为2:同时调度
                move1.setMoveType(2);
                moveMapper.updateByPrimaryKey(move1);
                //改变: 将职位设置为调度后的职位
                employee.setPositionNumber((String) param.get("position"));
            }
            //修改被调度的员工信息
            employee.setDepartmentNumber(afterDept.getId());
            employeeMapper.updateByPrimaryKey(employee);
        }
        if (type.equals("1")) {
            //职位调动
            //修改员工职位id
            employee.setPositionNumber((String) param.get("position"));
            employeeMapper.updateByPrimaryKey(employee);
            //向调度记录表插入数据 类型为1:职位调度
            Move move = new Move();
            move.setId(UUIDUtil.getUUID());
            move.setPositionBefore(employee.getPositionNumber());
            move.setPositionAfter((String) param.get("position"));
            move.setMoveType(1);
            move.setDeptBefore(employee.getDepartmentNumber());
            move.setManagerId(employee.getManageerId());
            move.setUpdateTime(date);
            move.setEmployeeNumber(employee.getId());
            moveMapper.insert(move);
        }
    }

    @Override
    public Employee getEmployee(String id) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo(id);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if (employeeList == null || employeeList.size() <= 0) {
            throw new NoDataException(400, "员工不存在!");
        }
        if (employeeList.size() > 1) {
            throw new DataException(502, "数据异常");
        }
        return employeeList.get(0);
    }
}
