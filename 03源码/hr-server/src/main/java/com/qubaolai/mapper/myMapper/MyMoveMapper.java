package com.qubaolai.mapper.myMapper;

import com.qubaolai.po.Move;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/9 19:08
 * @Description 自定义签到记录持久层接口
 */
public interface MyMoveMapper {
    public List<Move> getMoveLogs(Map<String, Object> param);
}
