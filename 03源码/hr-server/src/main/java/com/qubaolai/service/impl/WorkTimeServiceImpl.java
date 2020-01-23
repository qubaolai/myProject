package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.DataException;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Double difference = endTime.subtract(startTime).divide(new BigDecimal(3600000)).subtract(BigDecimal.ONE).doubleValue();
        return difference;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void statisticalWorkTime() {
        String date = DateUtil.getDate();
        //获取当前登录的员工
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria criteria = example.createCriteria();

        criteria.andDayEqualTo(DateUtil.getDate(DateUtil.subNowDays()));
        List<Attendance> attendances = attendanceMapper.selectByExample(example);
        if (null != attendances && 1 < attendances.size()) {
            throw new DataException(502, "数据异常");
        }
        String startTime = attendances.get(0).getStartTime();
        String endTime = attendances.get(0).getEndTime();
        Map<String, String> param = new HashMap<>();
        //声明前一天工时
        Double workTime = null;
        //签到开始时间为空
        if (null == startTime || "".equals(startTime)) {
            workTime = 0.0;
        }
        //下班签到时间为空
        if (null == endTime || "".equals(endTime)) {
            workTime = 8.0;
        }
        if (null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)) {
            param.put("startTime", startTime);
            param.put("endTime", endTime);
            Double time = calculateWorkTime(param);
            workTime = time;
        }
        WorkTime workTime1 = new WorkTime();
        //主键
        workTime1.setId(UUIDUtil.getUUID());
        //修改时间
        workTime1.setDay(date);
        //查询当前登录员工信息
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
        criteria1.andIdEqualTo(attendances.get(0).getEmployeeNumber());
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if(null == employees || 0 >= employees.size()){
            throw new DataException(400, "获取员工信息为空");
        }
        //获取当前员工的部门id
        String departmentNumber = employees.get(0).getDepartmentNumber();
        workTime1.setDepartmentNumber(departmentNumber);
        workTime1.setEmployeeNumber(attendances.get(0).getEmployeeNumber());
        //设置工时
        workTime1.setWorkingHours(workTime.toString());
        //查询当前员工之前是否存在计算工时的记录
        WorkTimeExample workTimeExample = new WorkTimeExample();
        WorkTimeExample.Criteria criteria2 = workTimeExample.createCriteria();
        criteria2.andEmployeeNumberEqualTo(workTime1.getEmployeeNumber());
        //获取当前年月日前一天
        criteria2.andDayEqualTo(DateUtil.getDate(DateUtil.subNowDays()));
        List<WorkTime> workTimes = workTimeMapper.selectByExample(workTimeExample);
        //为空则插入
        if(null == workTimes || 0 >= workTimes.size()){
            workTimeMapper.insert(workTime1);
        }else{
            //获取上次的工时 累加计算 然后修改
            Double workingHours = Double.valueOf(workTimes.get(0).getWorkingHours());
            BigDecimal time = new BigDecimal(workingHours);
            BigDecimal newTime = new BigDecimal(workTime);
            BigDecimal endWorkTime = time.add(newTime);
            workTime1.setWorkingHours(endWorkTime.toString());
            workTimeMapper.updateByExampleSelective(workTime1,workTimeExample);
        }



    }
}
