package com.qubaolai.service;

import com.qubaolai.po.Department;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据条件获取部门列表
     * @param param
     * @return
     */
    public List<Department> getDeptsByConditions(Map<String, Object> param);

    /**
     * 通过部门名称查询部门信息
     * @param name
     * @return
     */
    public Department getDeptByName(String name);

    /**
     * 修改部门信息
     * @param param
     * @return
     */
    public void updateDept(Map<String, Object> param);

    /**
     * 添加部门信息
     * @param param
     */
    public void insertdept(Map<String, Object> param);

    /**
     * 通过主键删除部门记录
     * @param id
     */
    public void deleteDept(String id);

    /**
     * 获取所有部门
     * @return
     */
    public List<Department> getAll();
}
