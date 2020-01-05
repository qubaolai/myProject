package com.qubaolai.mapper.myMapper;

import com.qubaolai.po.Employee;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/5 18:40
 * @Description 自定义员工查询接口
 */
public interface MyEmployeeMapper {
    /**
     * 根据条件查询员工
     * @param param
     * @return
     */
    public List<Employee> getEmployeeByConditions(Map<String, Object> param);
}
