package com.qubaolai.mapper.myMapper;

import com.qubaolai.po.Lea;

import java.util.List;
import java.util.Map;

public interface MyLeaMapper {
    public Integer getLeasByConditions(Map<String, Object> param);
}
