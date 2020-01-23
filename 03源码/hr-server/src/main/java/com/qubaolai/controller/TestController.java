package com.qubaolai.controller;

import com.qubaolai.service.WorkTimeService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TestController
 * @Author qubaolai
 * @Date 2020/01/21 17:01
 * @Description 
 */
@RestController
public class TestController {
    @Resource
    private WorkTimeService workTimeService;
    @RequestMapping("/test")
    public ResultVo test(){
        workTimeService.statisticalWorkTime();
        return ResultVo.sendResult(200,"success");
    }
}