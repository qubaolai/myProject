package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.service.LeaService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName LeaController
 * @Author qubaolai
 * @Date 2020/01/19 9:35
 * @Description 假期相关
 */
@RestController
@RequestMapping("/lea")
public class LeaController {
    @Resource
    private LeaService leaService;

    /**
     * 请假申请
     * @param param
     * @return
     */
    @PostMapping("/applyHoliday")
    public ResultVo applyHoilday(@RequestBody Map<String, Object> param) {
        if (null == param) {
            throw new ParamException(501, "参数异常");
        }
        ResultVo resultVo = leaService.applyHoilday(param);
        return resultVo;
    }

    /**
     * 按条件查询请假信息
     * @param param
     * @return
     */
    @GetMapping("/getLeasByConditions")
    public ResultVo getLeasByConditions(@RequestBody Map<String, Object> param){
        if(null == param){
            throw new ParamException(501, "参数异常!");
        }
        ResultVo result = leaService.getLeasByConditions(param);
        return result;
    }

    /**
     * 审批请假信息
     * @param leaId
     * @return
     */
    @RequestMapping("/approvalHoliday")
    public ResultVo approvalHoliday(String leaId){
        if(null == leaId || "".equals(leaId)){
            throw new ParamException(501, "参数异常");
        }
        return null;
    }
}