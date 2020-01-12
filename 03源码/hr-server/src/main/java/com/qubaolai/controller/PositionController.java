package com.qubaolai.controller;

import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:42
 * @Description 职称类控制
 */
@RestController
@RequestMapping("position")
public class PositionController {
    @Resource
    private PositionService positionService;
}
