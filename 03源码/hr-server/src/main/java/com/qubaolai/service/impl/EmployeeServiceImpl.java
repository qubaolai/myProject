package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.LoginException;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/30 14:13
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public ResultVo login(Employee employee) {
        //查询用户
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(employee.getEmployeeNumber());
        List<Employee> employees = employeeMapper.selectByExample(example);
        //判空
        if(employees == null || employees.size() <= 0){
            throw new NoDataException("用户不存在！");
        }
        //判断用户名密码
        if(!employee.getPassword().equals(employees.get(0).getPassword())){
            throw  new LoginException("密码错误！");
        }
        //生成token
        String token = JWTUtil.encrypt(employee);
        //定义返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("user", employees.get(0));
        map.put("token",token);
        return ResultVo.sendResult(200,"success",map);
    }
}
