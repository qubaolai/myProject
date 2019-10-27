package com.guanghua;

import com.guanghua.common.utils.UUIDUtil;
import com.guanghua.mapper.DepartmentMapper;
import com.guanghua.mapper.EmployeeMapper;
import com.guanghua.po.Employee;
import com.guanghua.po.EmployeeExample;
import com.guanghua.service.EmployeeService;
import com.guanghua.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Slf4j
public class HRTest {
    @Resource
    private ApplicationContext applicationContext;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentMapper departmentMapper;
    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        // 导入需要测试的
        employeeService = applicationContext.getBean(EmployeeService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        Employee employee = employeeService.selectEmployeeById("aaa");
        log.info("asd");
        System.out.println(1);
    }
    @Test
    public void test2(){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeeNumberEqualTo("asd");
        int i = employeeMapper.deleteByExample(employeeExample);
        System.out.println(i);
    }

    @Test
    public void test1(){
        Employee employee = new Employee();
        employee.setId(UUIDUtil.getUUID());
        employee.setEmployeeNumber("asd");
        employee.setPassword("asd");
        employeeService.insertEmployeeByemp(employee);
        System.out.println(1);
    }

}
