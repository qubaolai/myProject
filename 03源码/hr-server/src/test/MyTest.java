import com.qubaolai.common.basic.BaseService;
import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.Position;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qubaolai
 * @date 2019/10/30 14:04
 */
@Slf4j
public class MyTest {
    private ApplicationContext applicationContext;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private BaseService baseService;
    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        positionMapper = applicationContext.getBean(PositionMapper.class);
        baseService = applicationContext.getBean(BaseService.class);
    }
    @Test
    public void test1(){
        Position position = new Position();
        position.setId(UUIDUtil.getUUID());
        position.setName("老大");
        position.setPositionNumber(1);
        position.setDepartmentNumber(1);
        position.setNotes("骨伤外科部职位");
        int insert = positionMapper.insert(position);
        System.out.println(1);
    }
    @Test
    public void test2(){
        Employee employee = employeeMapper.selectByPrimaryKey("9004f903dd8541e685fb2328fc1a872b");
        Date birthday = employee.getBirthday();
        String convert = DateUtil.convert(birthday);
        String s = DateUtil.convert2String(birthday, "YYYY-MM-dd");
        Date date = DateUtil.convert2Date(s, "YYYY-MM-dd");
        System.out.println(date);
    }
    @Test
    public void test3(){
        Date date = new Date();

    }
}
