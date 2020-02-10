package com.qubaolai.pojo;

import com.qubaolai.po.Department;
import com.qubaolai.po.Employee;
import com.qubaolai.po.Position;
import lombok.Data;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/8 13:17
 * @Description 调动记录类父类
 */
@Data
public class MoveFather {
    @Resource
    private String empName;
    @Resource
    private String deptBefore;
    @Resource
    private String deptAfter;
    @Resource
    private String positionBefore;
    @Resource
    private String positionAfter;
}
