package com.qubaolai.service;

import com.qubaolai.po.Employee;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2019/12/26 21:26
 * @Description 员工工时相关业务
 */
public interface WorkTimeService {
    /**
     * 计算员工当天工时
     * @param map
     */
    public Double calculateWorkTime(String startStr,String endStr);

    /**
     * 用于每天凌晨1点统计前一天的员工工时
     * @return
     */
    public void statisticalWorkTime();
}
