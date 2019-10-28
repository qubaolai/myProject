package com.guanghua.service.impl;

import com.guanghua.common.exception.exceptions.UserException;
import com.guanghua.mapper.EmployeeMapper;
import com.guanghua.po.Employee;
import com.guanghua.po.EmployeeExample;
import com.guanghua.pojo.EmployeeExtend;
import com.guanghua.service.EmployeeService;
import com.guanghua.vo.ResultVo;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/27 19:01
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public ResultVo login(Employee employee) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeeNumberEqualTo(employee.getEmployeeNumber());
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if(employees == null){
            throw new UserException("用户不存在");
        }
        if(employees.get(0).getPassword().equals(employee.getPassword())){
            throw new UserException("用户密码错误！");
        }
        String token = new EmployeeExtend().getToken(employee);
        Map<String,Object> map = new HashMap<>();
        map.put("user", employees.get(0));
        map.put("token", token);
        return ResultVo.sendResult(200,"success",map);
    }
}
