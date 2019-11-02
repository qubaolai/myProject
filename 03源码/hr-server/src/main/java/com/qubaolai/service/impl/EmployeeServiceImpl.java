package com.qubaolai.service.impl;

import com.qubaolai.common.basic.impl.BaseServiceImpl;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.LoginException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.MD5Tools;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.LogsMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.po.Logs;
import com.qubaolai.po.LogsExample;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public void updateEmployee(Employee employee) {
        if(employee != null){
            employeeMapper.updateByPrimaryKeySelective(employee);
        }
    }
    @Override
    public ResultVo login(Employee employee) {
        //查询用户
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(MD5Tools.string2MD5(employee.getEmployeeNumber()));
        List<Employee> employees = employeeMapper.selectByExample(example);
        //判空
        if (employees == null || employees.size() <= 0) {
            throw new NoDataException(400,"用户不存在！");
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
            throw new LoginException(208,ErrorEmnus.getMsg(208));
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
                throw new LoginException(199,"密码已错误四次");
            }
            return ResultVo.sendResult(204, ErrorEmnus.getMsg(204));
        }
        return ResultVo.sendResult(200, "success", employees.get(0));
    }

    @Override
    public ResultVo updatePassword(Map<String, String> map) {
        //获取当前登录用户
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        //判空
        if (StringUtils.isBlank(map.get("newPassword"))) {
            throw new ParamException(400,"新密码为空！");
        }
        if (StringUtils.isBlank(map.get("oldPassword"))) {
            throw new ParamException(400,"旧密码为空！");
        }
        if (StringUtils.isBlank(map.get("confirmPassword"))) {
            throw new ParamException(400,"确认密码为空！");
        }
        //判断密码是否同时包含字母和数字
        if (map.get("newPassword").matches(".*[^a-zA-Z0-9]+.*") ||
                map.get("newPassword").matches("^[0-9]+$") ||
                map.get("newPassword").matches("^[a-zA-Z]+$")) {
            return ResultVo.sendResult(500, "密码必须包含数字和字母!");
        }
        //判断旧密码与新密码是否相同
        if (StringUtils.equals(map.get("newPassword"), map.get("oldPassword"))) {
            return ResultVo.sendResult(205, ErrorEmnus.getMsg(205));
        }
        //判断新密码与确认密码是否相同
        if (StringUtils.equals(map.get("newPassword"), map.get("confirmPassword"))) {
            return ResultVo.sendResult(207, ErrorEmnus.getMsg(207));
        }
        currentLoginEmployee.setPassword(MD5Tools.string2MD5(map.get("newPassword")));
        employeeService.updateEmployee(currentLoginEmployee);
        return ResultVo.sendResult(200,"success");
    }
}
