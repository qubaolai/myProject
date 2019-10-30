package com.qubaolai.common.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.common.utils.JWTUtil;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.EmployeeExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
public class HrHandlerIntercepter implements HandlerInterceptor {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        if(StringUtils.isBlank(token)){
            throw new NoTokenException("无token，请重新登录");
        }
        String userId = "";
        String dateString = "";
        try {
            Map<String, String> decrypt = JWTUtil.decrypt(token);
            userId = decrypt.get("empNum");
            dateString = decrypt.get("date");
        } catch (JWTDecodeException j) {
            throw new NoTokenException("token信息缺失，请重新登录");
        }
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(userId);
        List<Employee> employees = employeeMapper.selectByExample(example);
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(employees.get(0).getPassword() + dateString)).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new NoTokenException("用户名密码错误，请重新登录");
        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",employees.get(0));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
