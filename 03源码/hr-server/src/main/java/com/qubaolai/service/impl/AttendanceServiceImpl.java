package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.AttendanceMapper;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.AttendanceService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    /**
     * 插入签到记录
     * @param map
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void singIn(Map<String, Object> map){
        Attendance attendance = null;
        if(null == (Attendance)map.get("attendance")){
            attendance = new Attendance();
            attendance.setId(UUIDUtil.getUUID());
        }else{
            attendance = (Attendance)map.get("attendance");
        }
        attendance.setEmployeeNumber(((Employee)map.get("user")).getId());
        attendance.setDepartmentNumber(((Employee)map.get("user")).getDepartmentNumber());
        //向签到记录添加数据
        attendance.setDay(DateUtil.getDate());
        if(null != map.get("startTime")){
            Date startTime = (Date) map.get("startTime");
            attendance.setStartTime(DateUtil.convert2String(startTime,"yyyy-MM-dd HH:mm:ss"));
        }
        if(null != map.get("startType")){
            attendance.setStartType((Integer)map.get("startType"));
        }
        if(null != map.get("endTime")){
            attendance.setEndTime(DateUtil.convert2String((Date)map.get("endTime"),"yyyy-MM-dd HH:mm:ss"));
        }
        if(null != map.get("endType")){
            attendance.setEndType((Integer)map.get("endType"));
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
        criteria.andEmployeeNumberEqualTo(employeeService.getCurrentLoginEmployee().getId());
        criteria.andDayEqualTo(DateUtil.getDate());
        List<Attendance> attendances = attendanceMapper.selectByExample(example);
        if(0 >= attendances.size() || null == attendances){
            return ResultVo.sendResult(400,"上班未签到,请联系人事管理!");
        }
        return ResultVo.sendResult(200, "success",attendances.get(0));
    }

    /**
     * 员工下班签到
     * @param map
     */
    @Override
    public void outWorkSingIn(Map<String, Object> map) {
        //获取上班签到记录
        Attendance attendance = (Attendance) map.get("attendance");
        attendance.setEndTime(DateUtil.convert2String((Date)map.get("endTime"),"yyyy-MM-dd HH:mm:ss"));
        attendance.setEndType((Integer)map.get("endType"));
        attendanceMapper.updateByPrimaryKey(attendance);
    }

    /**
     * 上下班签到
     *
     */
    @Override
    public ResultVo workSingIn() {
        //签到记录参数
        Map<String, Object> param = new HashMap<>();
        param.put("user", employeeService.getCurrentLoginEmployee());
        //获取正常上班签到时间(8:00-9:00)
        Date eight = DateUtil.getTime(8);
        Date nine = DateUtil.getTime(9);
        //获取12:00(区分上午下午)
        Date twelve = DateUtil.getTime(12);
        //获取正常下班打卡时间(17:00)
        Date seventeen = DateUtil.getTime(17);
        //获取加班工时计时开始时间(19:00)
        Date nineteen = DateUtil.getTime(19);
        //获取当前时间
        Date date = new Date();
        //上班
        if (date.before(twelve)) {
            //根据用户ID和当前年月日查询签到记录
            //如果记录存在且上班签到时间不为空
            //提示已签到
            //否则插入记录 签到成功
            ResultVo resultVo = selectGoWorkAttendanceLog();
            if (400 == resultVo.getCode()) {
                //签到记录不存在
                if (date.before(twelve)) {
                    //正常上班
                    if (date.before(eight) || (date.after(eight) && date.before(nine))) {
                        param.put("startTime", date);
                        param.put("startType", 0);
                    }
                    //上班迟到
                    if (date.after(nine) && date.before(twelve)) {
                        param.put("startTime", date);
                        param.put("startType", 1);
                    }
                    //插入上班签到记录
                    singIn(param);
                    return ResultVo.sendResult(200, "签到成功!");
                }
                //签到时间过期
                if(date.after(twelve)){
                    //插入上班签到记录
                    param.put("startTime", date);
                    param.put("startType", 2);
                    singIn(param);
                    return ResultVo.sendResult(200, "上午缺勤!");
                }
            }
            //查询记录存在
            return ResultVo.sendResult(200, "您已签到!");
        }
        //下班
        if (date.after(twelve)) {
            ResultVo resultVo = selectGoWorkAttendanceLog();
            param.put("attendance", resultVo.getData());
            //存在上班签到记录
            if(200 == resultVo.getCode()){
                Attendance attendance = (Attendance)resultVo.getData();
                if(null != attendance.getEndTime() && !"".equals(attendance.getEndTime())){
                    //已签到
                    return ResultVo.sendResult(200, "您已签到!");
                }
                //早退
                if(date.before(seventeen)){
                    param.put("endTime", date);
                    param.put("endType", 1);
                }
                //正常下班
                if(date.after(seventeen) && date.before(nineteen)){
                    param.put("endTime", date);
                    param.put("endType", 0);
                }
                //加班
                if(date.after(nineteen)){
                    param.put("endTime", date);
                    param.put("endType", 2);
                }
                //修改上班签到记录
                outWorkSingIn(param);
                return ResultVo.sendResult(200, "签到成功!");
            }
            //早退
            if(date.before(seventeen)){
                param.put("endTime", date);
                param.put("endType", 1);
            }
            //正常下班
            if(date.after(seventeen) && date.before(nineteen)){
                param.put("endTime", date);
                param.put("endType", 0);
            }
            //加班
            if(date.after(nineteen)){
                param.put("endTime", date);
                param.put("endType", 2);
            }
            singIn(param);
            return resultVo;
        }
        return null;
    }

    /**
     * 管理员修改员工签到记录
     * @param map
     */
    @Override
    public ResultVo updateSingInLog(Map<String, Object> map) {
        //获取正常上班签到时间(8:00-9:00)
        Date eight = DateUtil.getTime(8);
        Date nine = DateUtil.getTime(9);
        //获取12:00(区分上午下午)
        Date twelve = DateUtil.getTime(12);
        //获取正常下班打卡时间(17:00)
        Date seventeen = DateUtil.getTime(17);
        //获取加班工时计时开始时间(19:00)
        Date nineteen = DateUtil.getTime(19);
        //获取员工ID
        if(null == map.get("employeeNumber") || null == map.get("date")){
            throw new ParamException("参数异常!");
        }
        String employeeNumber = (String) map.get("employeeNumber");
        String dateStr = (String) map.get("date");
        String date = dateStr.substring(0,10);
        String startTime = null;
        Integer startType = null;
        String endTime = null;
        Integer endType = null;
        //获取上班签到时间
        if(null != map.get("startDate")){
            String startDateStr = (String)map.get("startDate");
            //将字符串时间转为date类型
            Date startDate = DateUtil.convert2Date(startDateStr, "yyyy-MM-dd HH:mm:ss");
            startTime = DateUtil.convert(startDate);
            //判断上班签到类型
            //正常上班
            if (startDate.before(eight) || (startDate.after(eight) && startDate.before(nine))) {
                startType = 0;
            }
            //上班迟到
            if (startDate.after(nine) && startDate.before(twelve)) {
                startType = 1;
            }
            //签到时间过期
            if(startDate.after(twelve)){
                startType = 2;
            }
        }
        //获取下班签到时间
        if(null != map.get("endDate")){
            String endDateStr = (String)map.get("endDate");
            //将字符串时间转为date类型
            Date endDate = DateUtil.convert2Date(endDateStr, "yyyy-MM-dd HH:mm:ss");
            endTime = DateUtil.convert(endDate);
            //判断下班签到类型
            //早退
            if(endDate.before(seventeen)){
                endType = 1;
            }
            //正常下班
            if(endDate.after(seventeen) && endDate.before(nineteen)){
                endType = 0;
            }
            //加班
            if(endDate.after(nineteen)){
                endType = 2;
            }
        }
        //修改DB数据
        //查询指定年月日是否存在记录
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria criteria = example.createCriteria();
        if(null == map.get("employeeNumber") || null == map.get("date")){
            throw new ParamException("参数异常!");
        }
        criteria.andEmployeeNumberEqualTo(employeeNumber);
        criteria.andDayEqualTo(date);
        List<Attendance> attendances = attendanceMapper.selectByExample(example);
        //存在 修改对应缺失的签到信息
        if(null != attendances && 0 < attendances.size()){
            Attendance attendance = attendances.get(0);
            attendance.setStartTime(startTime);
            attendance.setStartType(startType);
            attendance.setEndTime(endTime);
            attendance.setEndType(endType);
            //修改不为空的字段
            attendanceMapper.updateByPrimaryKeySelective(attendance);
            return ResultVo.sendResult(200, "修改成功!");
        }
        //不存在 插入一条该年月日的签到记录
        Attendance attendance = new Attendance();
        attendance.setId(UUIDUtil.getUUID());
        attendance.setEmployeeNumber(employeeNumber);
        attendance.setDay(date);
        attendance.setStartTime(startTime);
        attendance.setStartType(startType);
        attendance.setEndTime(endTime);
        attendance.setEndType(endType);
        attendanceMapper.insert(attendance);
        return ResultVo.sendResult(200, "修改成功!");
    }

    @Override
    public List<Attendance> getAttendance(Map<String, Object> param) {
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria attendanceCriteria = attendanceExample.createCriteria();
        if(null != param.get("empName") && !"".equals((String)param.get("empName"))){
            //通过员工姓名查询员工 模糊查询
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andNameLike("%"+(String)param.get("empName")+"%");
            List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
            if(employeeList == null || 0 >= employeeList.size()){
                throw new NoDataException(400, "查询数据为空");
            }
            ArrayList<String> empIds = new ArrayList<>();
            for(Employee employee : employeeList){
                empIds.add(employee.getId());
            }
            attendanceCriteria.andEmployeeNumberIn(empIds);
        }
        //员工编号模糊查询
        if(null != param.get("empNum") && !"".equals((String)param.get("empNum"))){
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andUsernameEqualTo((String)param.get("empNum"));
            List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
            ArrayList<String> empUName = new ArrayList<>();
            if(null != employeeList && 0 < employeeList.size()){
                for(Employee employee : employeeList){
                    empUName.add(employee.getId());
                }
            }
            attendanceCriteria.andEmployeeNumberIn(empUName);
        }
        //部门编号精确查询
        if(null != param.get("deptNum") && !"".equals((String)param.get("deptNum"))){
            attendanceCriteria.andDepartmentNumberEqualTo((String)param.get("deptNum"));
        }
        //签到时间
        //上班
        String singInstartTime = null;
        String singInEndTime = null;
        if(null != param.get("singInStartTime") && !"".equals((String)param.get("singInStartTime"))){
            singInstartTime = (String)param.get("singInStartTime");
        }
        if(null != param.get("singInEndTime") && !"".equals((String)param.get("singInEndTime"))){
            singInEndTime = (String)param.get("singInEndTime");
        }
        if(singInstartTime !=null && singInEndTime != null){
            attendanceCriteria.andStartTimeBetween(singInstartTime,singInEndTime);
        }
        //下班
        String singOutstartTime = null;
        String singOutEndTime = null;
        if(null != param.get("singOutstartTime") && !"".equals((String)param.get("singOutstartTime"))){
            singOutstartTime = (String)param.get("singOutstartTime");
        }
        if(null != param.get("singOutEndTime") && !"".equals((String)param.get("singOutEndTime"))){
            singOutEndTime = (String)param.get("singOutEndTime");
        }
        if(singOutstartTime !=null && singOutEndTime != null){
            attendanceCriteria.andEndTimeBetween(singOutstartTime,singOutEndTime);
        }
        //查询签到类型
        //上班
        if(null != param.get("singInType") && !"".equals((String)param.get("singInType"))){
            attendanceCriteria.andStartTypeEqualTo(Integer.parseInt((String)param.get("singInType")));
        }
        //下班
        if(null != param.get("singOutType") && !"".equals((String)param.get("singOutType"))){
            attendanceCriteria.andEndTypeEqualTo(Integer.parseInt((String)param.get("singOutType")));
        }
        //签到日期
        //开始
        String singDateStart = null;
        if(null != param.get("singDateStart") && !"".equals((String)param.get("singDateStart"))){
            singDateStart = (String)param.get("singDateStart");
        }
        //结束
        String singDateEnd = null;
        if(null != param.get("singDateEnd") && !"".equals((String)param.get("singDateEnd"))){
            singDateEnd = (String)param.get("singDateEnd");
        }
        if(singDateStart!=null && singDateEnd != null){
            attendanceCriteria.andDayBetween(singDateStart,singDateEnd);
        }
        List<Attendance> attendances = attendanceMapper.selectByExample(attendanceExample);
        if(null == attendances || 0 >= attendances.size() ){
            throw new NoDataException(400, "数据为空");
        }
        for(Attendance attendance : attendances){
            Employee employee = employeeMapper.selectByPrimaryKey(attendance.getEmployeeNumber());
            Department department = departmentMapper.selectByPrimaryKey(attendance.getDepartmentNumber());
            attendance.setEmployee(employee);
            attendance.setDepartment(department);
        }
        return attendances;
    }
}
