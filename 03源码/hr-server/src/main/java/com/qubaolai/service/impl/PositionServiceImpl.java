package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.Department;
import com.qubaolai.po.Position;
import com.qubaolai.po.PositionExample;
import com.qubaolai.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/1/9 20:38
 * @Description 职称业务实现类
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public List<Position> getOptions() {
        PositionExample example = new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        if(null == positions || 0 >= positions.size()){
            throw new NoDataException(400, "数据为空");
        }
        return positions;
    }

    @Override
    public void insertOption(Map<String, Object> param) {
        if(null == param.get("deptNumber") || "".equals((String)param.get("deptNumber"))){
            throw new ParamException(501, "参数异常");
        }
        Position position = new Position();
        position.setId(UUIDUtil.getUUID());
        Department department = departmentMapper.selectByPrimaryKey((String) param.get("deptNumber"));
        if(department == null){
            throw new NoDataException(400, "部门不存在");
        }
        position.setDepartmentNumber((String)param.get("deptNumber"));
        if(null == param.get("name") || "".equals((String)param.get("name"))){
            throw new ParamException(501, "参数异常");
        }
        position.setName((String)param.get("name"));
        if(null != param.get("notes") && !"".equals((String)param.get("notes"))){
            position.setNotes((String)param.get("notes"));
        }
        positionMapper.insert(position);
    }
}
