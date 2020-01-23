package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
