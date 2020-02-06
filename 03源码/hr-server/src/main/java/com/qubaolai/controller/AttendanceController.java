package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.po.Attendance;
import com.qubaolai.service.AttendanceService;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/1 14:15
 * @Description 员工签到操作相关
 */
@RestController
@RequestMapping("/attendance")
@Slf4j
public class AttendanceController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private AttendanceService attendanceService;

    /**
     * 员工签到
     *
     * @return
     */
    @GetMapping("/workSingIn")
    public ResultVo workSingIn() {
        ResultVo resultVo = attendanceService.workSingIn();
        return resultVo;
    }

    /**
     * 管理员修改员工签到
     *
     * @param map
     * @return
     */
    @PostMapping("/updateWorkSingIn")
    public ResultVo updateWorkSingin(@RequestBody Map<String, Object> map) {
        if (null == map) {
            throw new ParamException("参数异常");
        }
        ResultVo resultVo = attendanceService.updateSingInLog(map);
        return resultVo;
    }

    @PostMapping("/getAttendance")
    public ResultVo getAttendance(@RequestBody Map<String, Object> param){
        if(null == param){
            throw new ParamException(501, "参数异常");
        }
        List<Attendance> attendance = attendanceService.getAttendance(param);
        return ResultVo.sendResult(200, "success", attendance);
    }
}
