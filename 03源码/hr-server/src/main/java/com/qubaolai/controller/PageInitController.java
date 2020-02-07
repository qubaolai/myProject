package com.qubaolai.controller;

import com.qubaolai.po.Department;
import com.qubaolai.po.Position;
import com.qubaolai.service.DepartmentService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 22:04
 * @Description 页面初始化数据相关
 */
@RestController
@RequestMapping("/initPage")
public class PageInitController {
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;

    @RequestMapping("/init")
    public ResultVo initPage(){
        List<Department> departmentst = departmentService.getDepts();
        List<Position> options = positionService.getOptions();
        Map<String, Object> result = new HashMap<>();
        result.put("dept", departmentst);
        result.put("option", options);
        return ResultVo.sendResult(200, "initPage", result);
    }

    @GetMapping("/getPageEmpInfo")
    public ResultVo getPageEmpInfo(){
        Map<String, Object> pageInfo = departmentService.getPageInfo();
        return ResultVo.sendResult(200, "success", pageInfo);
    }
}
