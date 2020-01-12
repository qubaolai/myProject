package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.Position;
import com.qubaolai.po.PositionExample;
import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:38
 * @Description 职称业务实现类
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;
    @Override
    public List<Position> getOptions() {
        PositionExample example = new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        if(null == positions || 0 >= positions.size()){
            throw new NoDataException(400, "数据为空");
        }
        return positions;
    }
}
