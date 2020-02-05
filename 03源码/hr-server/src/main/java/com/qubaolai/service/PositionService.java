package com.qubaolai.service;

import com.qubaolai.po.Position;
import com.qubaolai.vo.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:37
 * @Description 职称类业务
 */
public interface PositionService {
    /**
     * 获取职称
     * @return
     */
    public List<Position> getOptions();

    /**
     * 添加职称
     * @param param
     */
    public void insertOption(Map<String, Object> param);

    /**
     * 通过部门id获取职称
     * @return
     */
    public List<Position> getPositionByDeptNum(String deptId);
}
