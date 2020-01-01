package com.qubaolai.service.impl;

import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.AttendanceMapper;
import com.qubaolai.po.Attendance;
import com.qubaolai.po.AttendanceExample;
import com.qubaolai.po.Employee;
import com.qubaolai.service.AttendanceService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2019/12/26 22:11
 * @Description 签到业务实现类
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private EmployeeService employeeService;
    /**
     * 插入签到记录
     * @param map
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void singin(Map<String, Object> map){
        Attendance attendance = new Attendance();
        attendance.setId(UUIDUtil.getUUID());
        attendance.setEmployeeNumber(((Employee)map.get("user")).getEmployeeNumber());
        //向签到记录添加数据
        attendance.setDay(DateUtil.getDate());
        if(null != map.get("startTime")){
            Date startTime = (Date) map.get("startTime");
            attendance.setStartTime(DateUtil.convert2String(startTime,"yyyy-MM-dd HH:mm:ss"));
        }
        if(null != map.get("startType")){
            attendance.setStartType((String)map.get("startType"));
        }
        if(null != map.get("endTime")){
            attendance.setEndTime(DateUtil.convert2String((Date)map.get("endTime"),"yyyy-MM-dd HH:mm:ss"));
        }
        if(null != map.get("endType")){
            attendance.setEndType((String)map.get("endType"));
        }
        attendanceMapper.insert(attendance);
    }

    /**
     * 查询签到记录是否存在
     * 条件:当前年月日
     * @return ResultVo
     */
    @Override
    public ResultVo selectGoWorkAttendanceLog() {
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria criteria = example.createCriteria();
        //当前登录系统用户
        criteria.andEmployeeNumberEqualTo(employeeService.getCurrentLoginEmployee().getEmployeeNumber());
        criteria.andDayEqualTo(DateUtil.getDate());
        List<Attendance> attendances = attendanceMapper.selectByExample(example);
        if(0 >= attendances.size() || null == attendances){
            return ResultVo.sendResult(400,"上班未签到,请联系人事管理!");
        }
        return ResultVo.sendResult(200, "success",attendances.get(0));
    }

    /**
     * 修改上班签到记录添加下班时间和类型
     * @param map
     */
    @Override
    public void updateSingin(Map<String, Object> map) {
        //获取上班签到记录
        Attendance attendance = (Attendance) map.get("attendance");
        attendance.setEndTime(DateUtil.convert2String((Date)map.get("endTime"),"yyyy-MM-dd HH:mm:ss"));
        attendance.setEndType((String)map.get("endType"));
        attendanceMapper.updateByPrimaryKey(attendance);
    }

}
