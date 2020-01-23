package com.qubaolai.common.quartz;

import com.qubaolai.service.WorkTimeService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ExampleJob
 * @Author qubaolai
 * @Date 2020/01/21 15:27
 * @Description 定时任务类
 */
public class ExampleJob {
    @Resource
    private WorkTimeService workTimeService;
    /**
     * 执行定时统计任务
     * 自行指定方法
     */
    public void execute(){
        //每天凌晨一点统计当天工时
        workTimeService.statisticalWorkTime();
    }
}