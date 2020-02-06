package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.AttendanceMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.WorkTimeMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.service.WorkTimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description qubaolai
 * @Date 2019/12/26 21:27
 * @Description 员工工时业务实现类
 */
@Service
public class WorkTimeServiceImpl implements WorkTimeService {
    @Resource
    private WorkTimeMapper workTimeMapper;
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private EmployeeService employeeService;

    /**
     * 计算员工工时
     *
     * @param
     */
    @Override
    public Double calculateWorkTime(String startStr,String endStr) {
        //将字符串时间转为时间戳
        Long StartTimeStamp = DateUtil.getTimeStamp(startStr);
        Long endStrStamp = DateUtil.getTimeStamp(endStr);
        BigDecimal startTime = new BigDecimal(StartTimeStamp);
        BigDecimal endTime = new BigDecimal(endStrStamp);
        //时级
        BigDecimal subtract = endTime.subtract(startTime).divide(new BigDecimal(3600000)).subtract(BigDecimal.ONE);
        double result = subtract.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void statisticalWorkTime() {
        //获取当前年月日的前一天
        Date date = DateUtil.subNowDays();
        String yesterday = DateUtil.getDate(date);
        //查询当前日期的前一天的所有签到记录
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria criteria = attendanceExample.createCriteria();
        criteria.andDayEqualTo(yesterday);
        List<Attendance> attendances = attendanceMapper.selectByExample(attendanceExample);
        Map<String, Attendance> maps = new HashMap<>();
        if (null == attendances || 0 >= attendances.size()) {
            throw new NoDataException(400, "签到记录为空!");
        }
        //遍历签到记录 存入map
        for (Attendance attendance : attendances) {
            maps.put(attendance.getEmployeeNumber(), attendance);
        }
        if (null == maps) {
            throw new NoDataException(400, "签到记录集合为空");
        }
        //循环map 计算工时并存入db
        for (Map.Entry<String, Attendance> map : maps.entrySet()) {
            //获取到map的key
            String key = map.getKey();
            Attendance attendance = map.getValue();
            Double workTime = null;
            if (key.equals(attendance.getEmployeeNumber())) {
                /**
                 * 计算工时
                 */
                //如果下班签到为空则工时计算为8小时
                if (null == attendance.getEndTime() || "".equals(attendance.getEndTime())) {
                    workTime = 4.0;
                }
                //上班签到记录为空则工时计算为0
                if (null == attendance.getStartTime() || "".equals(attendance.getStartTime())) {
                    workTime = 0.0;
                }
                if (null != attendance.getStartTime() && null != attendance.getEndTime() && !"".equals(attendance.getStartTime()) && !"".equals(attendance.getEndTime())) {
                    workTime = calculateWorkTime(attendance.getStartTime(), attendance.getEndTime());
                }
                //向工时统计表中插入记录
                WorkTime workTimePo = new WorkTime();
                workTimePo.setId(UUIDUtil.getUUID());
                //查询员工所在部门
                EmployeeExample employeeExample = new EmployeeExample();
                EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
                criteria1.andIdEqualTo(key);
                List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
                workTimePo.setDepartmentNumber(employeeList.get(0).getDepartmentNumber());
                workTimePo.setEmployeeNumber(key);
                workTimePo.setDay(DateUtil.getDate());
                workTimePo.setWorkingHours(workTime.toString());
                workTimeMapper.insert(workTimePo);
            }
        }
    }
}
