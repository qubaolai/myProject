package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.po.Position;
import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:42
 * @Description 职称类控制
 */
@RestController
@RequestMapping("/position")
public class PositionController {
    @Resource
    private PositionService positionService;

    @PostMapping("/insertPosition")
    public ResultVo insertPosition(@RequestBody Map<String, Object> param){
        if(null == param){
            throw new ParamException(501, "参数异常");
        }
        positionService.insertOption(param);
        return ResultVo.sendResult(200, "success");
    }

    @GetMapping("/getPositionByDeptId")
    public ResultVo getPositionByDeptId(String deptId){
        if(deptId == null || "".equals(deptId)){
            throw new ParamException(501, "参数异常");
        }
        List<Position> positionByDeptNum = positionService.getPositionByDeptNum(deptId);
        return ResultVo.sendResult(200, "success", positionByDeptNum);
    }
}
