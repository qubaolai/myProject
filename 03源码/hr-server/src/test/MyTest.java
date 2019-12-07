import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qubaolai.common.basic.BaseService;
import com.qubaolai.common.basic.impl.BaseServiceImpl;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.common.utils.*;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import com.qubaolai.po.Position;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PositionMapper positionMapper;
    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        positionMapper = applicationContext.getBean(PositionMapper.class);
    }
    @Test
    public void test1(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRlIjoiMjAxOS0xMS0xMCAxNDowMTowNCIsImF1ZCI6ImFzZCIsImFzZCI6ImFzZCJ9.6UKnNVOWE0FQSlg3VeSTd1oRKVI13nqXOEI2EDs0-yI";
        String userId = "";
        String dateString = "";
        try {
            Map<String, String> decrypt = JWTUtil.decrypt(token);
            userId = decrypt.get("empNum");
            dateString = decrypt.get("date");
        } catch (JWTDecodeException j) {
            throw new NoTokenException(303, "token信息缺失，请重新登录");
        }
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo("asd");
        List<Employee> employees = employeeMapper.selectByExample(example);
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(employees.get(0).getPassword() + dateString)).build();
            jwtVerifier.verify(token);
        } catch (UnsupportedEncodingException e) {
            throw new NoTokenException(204, ErrorEmnus.getMsg(204));
        }
        System.out.println(1);
    }
    @Test
    public void test2(){
        Employee employee = employeeMapper.selectByPrimaryKey("9004f903dd8541e685fb2328fc1a872b");
        Date birthday = employee.getBirthday();
        String convert = DateUtil.convert(birthday);
        String s = DateUtil.convert2String(birthday, "YYYY-MM-dd");
        Date date = DateUtil.convert2Date(s, "YYYY-MM-dd");
        String asd = MD5Tools.string2MD5("asd");
        String a = MD5Tools.convertMD5(asd);
        System.out.println(asd);
    }
    @Test
    public void test3(){
        String check = "^[a-zA-Z0-9]\\w{5}$";
        String password = "As2_";
        //是否包含数字
        boolean num = PasswordCheckUtil.checkContainDigit(password);
        //是否包含大写字母
        boolean upperCase = PasswordCheckUtil.checkContainUpperCase(password);
        //是否包含小写字母
        boolean lowerCase = PasswordCheckUtil.checkContainLowerCase(password);
        //是否包含特殊字符
        boolean specialChar = PasswordCheckUtil.checkContainSpecialChar(password);
        System.out.println("是否包含数字"+num);
        System.out.println("是否包含大写字母"+upperCase);
        System.out.println("是否包含小写字母"+lowerCase);
        System.out.println("是否包含特殊字符"+specialChar);
    }
    @Test
    public void test(){
        Employee employee = new Employee();
        employee.setId(UUIDUtil.getUUID());
        employee.setEmployeeNumber("asd");
        employee.setName("曲宝来");
        employee.setGender("男");
        employee.setBirthday(new Date());
        employee.setDepartmentNumber(1);
        employee.setPassword("asd");
        employeeMapper.insert(employee);
    }
    @Test
    public void test5(){
        String asd = MD5Tools.string2MD5("asd");
        log.info(asd);
    }

}
