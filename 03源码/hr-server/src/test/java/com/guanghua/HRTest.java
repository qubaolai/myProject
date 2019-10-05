package com.guanghua;

import com.guanghua.common.utils.UUIDUtil;
import com.guanghua.po.Employee;
import com.guanghua.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

@Slf4j
public class HRTest {

    private ApplicationContext applicationContext;
    @Resource
    private EmployeeService employeeService;
    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        employeeService = applicationContext.getBean(EmployeeService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        Employee employee = employeeService.selectEmployeeById("asd");
        log.info("asd");
        System.out.println(1);
    }

    @Test
    public void test1(){
        Employee employee = new Employee();
        employee.setId(UUIDUtil.getUUID());
        employee.setEmployeeNumber("asd");
        employee.setPassword("asd");
    }
}