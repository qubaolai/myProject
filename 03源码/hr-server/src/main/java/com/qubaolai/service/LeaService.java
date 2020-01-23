package com.qubaolai.service;

import com.qubaolai.po.Lea;
import com.qubaolai.vo.ResultVo;

import java.util.Map;

public interface LeaService {
    /**
     * 申请假期
     * @param param
     * @return
     */
    public ResultVo applyHoilday(Map<String, Object> param);

    /**
     * 审批请假信息
     * @param param
     * @return
     */
    public void approvalHoliday(Map<String, Object> param);

    /**
     * 按条件查询请假信息
     * @param param
     * @return
     */
    public ResultVo getLeasByConditions(Map<String, Object> param);
}
