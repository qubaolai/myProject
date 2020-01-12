package com.qubaolai.service;

import com.qubaolai.po.Department;
import com.qubaolai.vo.ResultVo;

import java.util.List;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:30
 * @Description 部门业务接口
 */
public interface DepartmentService {
    /**
     * 获取所有部门
     * @return
     */
    public List<Department> getDepts();
}
