package com.qubaolai.service;

import com.qubaolai.po.Attendance;
import com.qubaolai.vo.ResultVo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2019/12/26 22:08
 * @Description 签到相关业务
 */
public interface AttendanceService {
    /**
     * 插入签到记录
     * @param map
     */
    public void singin(Map<String, Object> map);

    /**
     * 查询上班签到记录
     * @return ResultVo
     */
    public ResultVo selectGoWorkAttendanceLog();

    /**
     * 查询下班签到记录
     * @return
     */
    public ResultVo selectAfterWorkAttendanceLog();
}
