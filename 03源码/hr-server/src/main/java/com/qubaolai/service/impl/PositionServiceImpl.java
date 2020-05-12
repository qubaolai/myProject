package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.Department;
import com.qubaolai.po.DepartmentExample;
import com.qubaolai.po.Position;
import com.qubaolai.po.PositionExample;
import com.qubaolai.service.PositionService;
import com.qubaolai.vo.ResultVo;
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

    @Override
    public List<Position> getPositionByDeptNum(String deptId) {
        PositionExample example = new PositionExample();
        PositionExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentNumberEqualTo(deptId);
        List<Position> positions = positionMapper.selectByExample(example);
        if(positions == null || positions.size() <= 0){
            throw new NoDataException(400, "职称为空");
        }
        return positions;
    }

    @Override
    public ResultVo savePisition(Position position) {
        position.setId(UUIDUtil.getUUID());
        PositionExample example = new PositionExample();
        PositionExample.Criteria criteria = example.createCriteria();
        criteria.andDepartmentNumberEqualTo(position.getDepartmentNumber());
        criteria.andNameEqualTo(position.getName());
        List<Position> positions = positionMapper.selectByExample(example);
        if(positions == null || 0 >= positions.size()){
            positionMapper.insert(position);
            return ResultVo.sendResult(200, "success");
        }
        return ResultVo.sendResult(202, "职位已存在");
    }

    @Override
    public List<Position> getPositionByDeptName(String deptName) {
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(deptName);
        List<Department> departments = departmentMapper.selectByExample(example);
        Department department = departments.get(0);
        PositionExample example1 = new PositionExample();
        PositionExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andDepartmentNumberEqualTo(department.getId());
        List<Position> positions = positionMapper.selectByExample(example1);
        return positions;
    }

}
