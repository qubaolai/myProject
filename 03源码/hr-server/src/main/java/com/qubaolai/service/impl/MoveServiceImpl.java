package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.MoveMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.mapper.myMapper.MyMoveMapper;
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
    private MyMoveMapper myMoveMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Move> getMoveLog(Map<String, Object> param) {
        Map<String, Object> params = new HashMap<>();
        if(param.get("empName") != null && !"".equals(param.get("empName"))){
            String empName = (String)((ArrayList)param.get("empName")).get(1);
            params.put("empName", empName);
        }
        if(param.get("empNum") != null && !"".equals(param.get("empNum"))){
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andUsernameEqualTo((String)param.get("empNum"));
            List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
            if(employeeList == null || 0 >= employeeList.size()){
                throw new NoDataException(400, "该员工不存在!");
            }
            Employee employee = employeeList.get(0);
            params.put("empNum", employee.getId());
        }
        if(param.get("deptNum") != null && !"".equals(param.get("deptNum"))){
            params.put("deptNum", (String)param.get("deptNum"));
        }
        if(param.get("position") != null && !"".equals(param.get("position"))){
            params.put("position", (String)param.get("position"));
        }
        if(param.get("moveType") != null && !"".equals(param.get("moveType"))){
            params.put("moveType", Integer.parseInt((String)param.get("moveType")));
        }
        if(null != param.get("moveDate") && !"".equals(param.get("moveDate"))){
            ArrayList<String> moveDate = (ArrayList) param.get("moveDate");
            String moveDateStart = moveDate.get(0);
            String moveDateEnd = moveDate.get(1);
            params.put("moveDateStart", moveDateStart);
            params.put("moveDateEnd", moveDateEnd);
        }
        List<Move> moveLogs = myMoveMapper.getMoveLogs(params);
        if(moveLogs == null || 0 >= moveLogs.size()){
            throw  new NoDataException(400, "查询记录为空!");
        }
        return moveLogs;
    }
}
