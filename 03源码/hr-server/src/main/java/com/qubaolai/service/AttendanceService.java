package com.qubaolai.service;

import com.qubaolai.vo.ResultVo;

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
    public void singIn(Map<String, Object> map);

    /**
     * 查询上班签到记录
     * @return ResultVo
     */
    public ResultVo selectGoWorkAttendanceLog();

    /**
     * 员工上班签到
     */
    public ResultVo workSingIn();

    /**
     * 员工上班签到
     * @param map
     */
    public void outWorkSingIn(Map<String, Object> map);

    /**
     *  管理员添加员工签到记录
     * @param map
     */
    public ResultVo updateSingInLog(Map<String, Object> map);

}
