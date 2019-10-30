import com.qubaolai.po.Employee;
import com.qubaolai.serve.EmployeeServe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@Slf4j
/**
 * @author qubaolai
 * @date 2019/10/29 11:53
 */

public class MyTest {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private EmployeeServe employeeServe;
    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 导入需要测试的
        employeeServe = applicationContext.getBean(EmployeeServe.class);
    }

    @Test
    public void selectEmp() {
        Employee employee = new Employee();
        employee.setEmployeeNumber("asd");
        List<Employee> employees = employeeServe.selectEmp(employee);
        log.info("asd");
        System.out.println(1);
    }
}
