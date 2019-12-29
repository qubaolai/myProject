package com.qubaolai.service.impl;

import com.qubaolai.common.basic.impl.BaseServiceImpl;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.LoginException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.MD5Tools;
import com.qubaolai.common.utils.PasswordCheckUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.LogsMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.AttendanceService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/30 14:13
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private LogsMapper logsMapper;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AttendanceService attendanceService;

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
        criteria.andEmployeeNumberEqualTo(employee.getEmployeeNumber());
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

    /**
     * 上下班签到
     *
     */
    @Override
    public ResultVo workSingIn() {
        //签到记录参数
        Map<String, Object> param = new HashMap<>();
        param.put("user", employeeService.getCurrentLoginEmployee());
        //获取正常上班签到时间(8:00-9:00)
        Date eight = DateUtil.getTime(8);
        Date nine = DateUtil.getTime(9);
        //获取12:00(区分上午下午)
        Date twelve = DateUtil.getTime(12);
        //获取正常下班打卡时间(17:00)
        Date seventeen = DateUtil.getTime(17);
        //获取加班工时计时开始时间(19:00)
        Date nineteen = DateUtil.getTime(19);
        //获取24:00(第二天零点)
        Date zero = DateUtil.getTime(24);
        //获取当前时间
        Date date = new Date();
        //上班
        if (date.before(twelve)) {
            //根据用户ID和当前年月日查询签到记录
            //如果记录存在且上班签到时间不为空
            //提示已签到
            //否则插入记录 签到成功
            ResultVo resultVo = attendanceService.selectGoWorkAttendanceLog();
            if (400 == resultVo.getCode()) {
                //签到记录不存在
                if (date.before(twelve)) {
                    //正常上班
                    if (date.before(eight) || (date.after(eight) && date.before(nine))) {
                        param.put("timeType", "上午");
                        param.put("startTime", date);
                        param.put("startType", "正常");
                    }
                    //上班迟到
                    if (date.after(nine) && date.before(twelve)) {
                        param.put("timeType", "上午");
                        param.put("startTime", date);
                        param.put("startType", "迟到");
                    }
                    //插入上班签到记录
                    attendanceService.singin(param);
                    return ResultVo.sendResult(200, "签到成功!");
                }
                //签到时间过期
                if(date.after(twelve)){
                    //插入上班签到记录
                    param.put("timeType", "下午");
                    param.put("startTime", date);
                    param.put("startType", "缺勤");
                    attendanceService.singin(param);
                    return ResultVo.sendResult(200, "上午缺勤!");
                }
            }
            //查询记录存在
            return ResultVo.sendResult(200, "您已签到!");
        }
        //下班
        if (date.after(twelve)) {
            ResultVo resultVo = attendanceService.selectGoWorkAttendanceLog();
            param.put("attendance", resultVo.getData());
            //存在上班签到记录
            if(200 == resultVo.getCode()){
                Attendance attendance = (Attendance)resultVo.getData();
                if(null != attendance.getEndTime() && !"".equals(attendance.getEndTime())){
                    //已签到
                    return ResultVo.sendResult(200, "您已签到!");
                }
                //早退
                if(date.before(seventeen)){
                    param.put("endTime", date);
                    param.put("endType", "早退");
                }
                //正常下班
                if(date.after(seventeen) && date.before(nineteen)){
                    param.put("endTime", date);
                    param.put("endType", "正常");
                }
                //加班
                if(date.after(nineteen)){
                    param.put("endTime", date);
                    param.put("endType", "加班");
                }
                //修改上班签到记录
                attendanceService.updateSingin(param);
                return ResultVo.sendResult(200, "签到成功!");
            }
            return resultVo;
        }
        return null;
    }
}
