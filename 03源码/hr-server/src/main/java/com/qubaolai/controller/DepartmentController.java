package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.po.Department;
import com.qubaolai.po.DepartmentExample;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:25
 * @Description 部门相关
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 查询部门
     *
     * @param param
     * @return
     */
    @RequestMapping("/departmentList")
    public ResultVo departmentList(@RequestBody Map<String, Object> param) {
        if (null == param) {
            throw new ParamException(501, "参数异常");
        }
        List<Department> depts = departmentService.getDeptsByConditions(param);
        return ResultVo.sendResult(200, "success", depts);
    }

    /**
     * 添加部门
     *
     * @param param
     * @return
     */
    @PostMapping("/insertDept")
    public ResultVo insertDept(@RequestBody Map<String, Object> param) {
        if (null == param) {
            throw new ParamException(501, "参数异常");
        }
        departmentService.insertdept(param);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteDepartment")
    public ResultVo deleteDept(String id) {
        if (null == id || "".equals(id)) {
            throw new ParamException(501, "参数异常");
        }
        departmentService.deleteDept(id);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 修改部门
     * @param param
     * @return
     */
    @PutMapping("/updateDepartment")
    public ResultVo updateDept(@RequestBody Map<String, Object> param) {
        if (null == param) {
            throw new ParamException(501, "参数异常");
        }
        departmentService.updateDept(param);
        return ResultVo.sendResult(200, "success");
    }

    @GetMapping("/getAllPartment")
    public ResultVo getAllDepartment(){
        List<Department> all = departmentService.getAll();
        return ResultVo.sendResult(200, "success", all);
    }
}
