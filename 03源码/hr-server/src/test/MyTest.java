import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.common.utils.*;
import com.qubaolai.mapper.DepartmentMapper;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.mapper.PositionMapper;
import com.qubaolai.mapper.myMapper.MyEmployeeMapper;
import com.qubaolai.po.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private MyEmployeeMapper myEmployeeMapper;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 导入需要测试的
        employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        positionMapper = applicationContext.getBean(PositionMapper.class);
        myEmployeeMapper = applicationContext.getBean(MyEmployeeMapper.class);
    }

    @Test
    public void test1() {
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
        criteria.andUsernameEqualTo("asd");
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
    public void test2() {
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
    public void test3() {
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
        System.out.println("是否包含数字" + num);
        System.out.println("是否包含大写字母" + upperCase);
        System.out.println("是否包含小写字母" + lowerCase);
        System.out.println("是否包含特殊字符" + specialChar);
    }

    @Test
    public void test() {
//        Employee employee = new Employee();
//        employee.setId(UUIDUtil.getUUID());
//        employee.setUsername("asd");
//        employee.setName("曲宝来");
//        employee.setGender("男");
//        employee.setBirthday(new Date());
//        employee.setDepartmentNumber(1);
//        employee.setPassword("asd");
//        employeeMapper.insert(employee);
    }

    @Test
    public void test5() {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, 24);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        Date m6 = c.getTime();
//        System.out.println(m6);
//        Map<String, Object> map = new HashMap<>();
//        map.put("ad","asd");
//        System.out.println(map.get("a"));
        String time = DateUtil.convert(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long timestamp = date.getTime();
        long timestamp1 = DateUtil.getTimestamp();
        System.out.println(timestamp);
        System.out.println(timestamp1);
    }

    @Test
    public void test6(){
    }
    @Test
//    @Pagination(countPerPage = "1", currentPageNumber = "2")
    public void test7(){
        Map<String, Object> param = new HashMap<>();
            param.put("empName", "姓名");
        //员工编号
            param.put("employeeNumber", "zxc");
        //入职时间
            param.put("inTime", DateUtil.convert2Date("2019-10-31","yyyy-MM-dd"));
        //性别
            param.put("gender", "男");
        //学历
            param.put("education", "本科");
            List<String> midList = new ArrayList<>();
            midList.add("04baca6236bf43b195198f97672114cc");
            midList.add("1bf4e22c1f1a46a59607522a81f3de0e");
            midList.add("3d337e5fa140474280a00fbe73d4d734");
            midList.add("455b65b57302415186948db4c736b041");
        param.put("midList", midList);
        List<Integer> didList = new ArrayList<>();
        didList.add(16);
        param.put("didList", didList);
        List<Employee> employeeByConditions = myEmployeeMapper.getEmployeeByConditions(param);
        System.out.println(1);
    }
    @Test
    public void test8(){
        String asd = MD5Tools.string2MD5("asd");
        System.out.println(1);
    }
}
