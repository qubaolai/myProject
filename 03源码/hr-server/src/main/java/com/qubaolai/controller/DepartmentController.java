package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.po.Department;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param param
     * @return
     */
    @RequestMapping("/departmentList")
    public ResultVo departmentList(@RequestBody Map<String, Object> param){
        if(null == param){
            throw new ParamException(501, "参数异常");
        }
        List<Department> depts = departmentService.getDeptsByConditions(param);
        return ResultVo.sendResult(200, "success", depts);
    }
}
