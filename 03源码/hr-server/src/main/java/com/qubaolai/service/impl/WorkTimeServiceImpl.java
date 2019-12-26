package com.qubaolai.service.impl;

import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.service.WorkTimeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2019/12/26 21:27
 * @Description 员工工时业务实现类
 */
@Service
public class WorkTimeServiceImpl implements WorkTimeService {
    /**
     * 计算员工工时
     * @param map
     */
    @Override
    public Double calculateWorkTime(Map<String, String> map) {
        //获取字符串时间
        String startStr = map.get("startTime");
        String endStr = map.get("endTime");
        //将字符串时间转为时间戳
        Long StartTimeStamp = DateUtil.getTimeStamp(startStr);
        Long endStrStamp = DateUtil.getTimeStamp(endStr);
        BigDecimal startTime = new BigDecimal(StartTimeStamp);
        BigDecimal endTime = new BigDecimal(endStrStamp);
        //时级
        Double difference = endTime.subtract(startTime).divide(new BigDecimal(3600000 )).doubleValue();
        return difference;
    }
}
