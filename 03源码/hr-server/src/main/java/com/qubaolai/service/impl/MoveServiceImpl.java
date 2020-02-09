package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.MoveMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.*;
import com.qubaolai.service.MoveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/8 9:07
 * @Description 员工调度业务实现
 */
@Service
public class MoveServiceImpl implements MoveService {
    @Resource
    private MoveMapper moveMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Move> getMoveLog(Map<String, Object> param) {
        MoveExample moveExample = new MoveExample();
        MoveExample.Criteria criteria = moveExample.createCriteria();
        if(null != param.get("empName") && !"".equals(param.get("empName"))){
            String empNum = (String)((ArrayList)param.get("empName")).get(1);
            criteria.andEmployeeNumberEqualTo(empNum);
        }
        if(null != param.get("empNum") && !"".equals(param.get("empNum"))){
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
            criteria1.andUsernameLike("%" + (String)param.get("empNum") + "%");
            List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
            if(employeeList == null || 0 >= employeeList.size()){
                throw new NoDataException(400, "数据为空!");
            }
            ArrayList<String> empNums = new ArrayList<>();
            for(Employee employee : employeeList){
                empNums.add(employee.getId());
            }
            criteria.andEmployeeNumberIn(empNums);
        }
        if(null != param.get("moveDate") && !"".equals(param.get("moveDate"))){
            ArrayList<String> moveDate = (ArrayList) param.get("moveDate");
            criteria.andUpdateTimeBetween(moveDate.get(0),moveDate.get(1));
        }
        //调动前部门
        if(null != param.get("deptNum") && !"".equals(param.get("deptNum"))){
            criteria.andDeptBeforeEqualTo((String)param.get("deptNum"));
        }
        //调动前职位
        if(null != param.get("position") && !"".equals(param.get("position"))){
            criteria.andPositionBeforeEqualTo((String)param.get("position"));
        }
        //调动类型
        if(null != param.get("moveType") && !"".equals(param.get("moveType"))){
            criteria.andMoveTypeEqualTo((Integer)(param.get("moveType")));
        }
        List<Move> moves = moveMapper.selectByExample(moveExample);
        if(moves == null || 0 >= moves.size()){
            throw new NoDataException(400, "数据为空!");
        }
        for(Move move : moves){
            Employee employee = employeeMapper.selectByPrimaryKey(move.getEmployeeNumber());
            move.setEmployee(employee);
            Department departmentBefore = departmentMapper.selectByPrimaryKey(move.getDeptBefore());
            Department departmentAfter = departmentMapper.selectByPrimaryKey(move.getDeptAfter());
            Map<String, Department> departmentMap = new HashMap<>();
            departmentMap.put("before",departmentBefore);
            departmentMap.put("after",departmentAfter);
            move.setDepartmentMap(departmentMap);
            Position positionBefore = positionMapper.selectByPrimaryKey(move.getPositionBefore());
            Position positionAfter = positionMapper.selectByPrimaryKey(move.getPositionAfter());
            Map<String, Position> positionMap = new HashMap<>();
            positionMap.put("before",positionBefore);
            positionMap.put("after",positionAfter);
            move.setPositionMap(positionMap);
        }
        return moves;
    }
}
