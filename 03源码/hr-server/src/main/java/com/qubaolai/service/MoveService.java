package com.qubaolai.service;

import com.qubaolai.po.Move;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/8 9:07
 * @Description 员工调动接口
 */
public interface MoveService {
    /**
     * 获取员工调度信息记录
     * @param param
     * @return
     */
    public List<Move> getMoveLog(Map<String, Object> param);
}
