import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/30 14:04
 */
@Slf4j
public class MyTest {
    private ApplicationContext applicationContext;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private EmployeeMapper employeeMapper;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        employeeMapper = applicationContext.getBean(EmployeeMapper.class);
    }
    @Test
    public void test1(){
    }
}
