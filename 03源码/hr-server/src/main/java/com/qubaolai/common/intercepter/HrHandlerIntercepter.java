package com.qubaolai.common.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.qubaolai.common.enums.ErrorEmnus;
import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.mapper.EmployeeMapper;
import com.qubaolai.po.Employee;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HrHandlerIntercepter implements HandlerInterceptor {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        if(StringUtils.isBlank(token)){
            throw new NoTokenException(ErrorEmnus.getMsg(201));
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new NoTokenException(ErrorEmnus.getMsg(202));
        }
        //查询员工是否存在 并验证用户名密码是否匹配
        Employee employee = employeeMapper.selectByPrimaryKey(userId);
        if(employee == null){
            throw new NoDataException(ErrorEmnus.getMsg(203));
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(employee.getPassword() + employee.getDeviceid())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new NoTokenException(ErrorEmnus.getMsg(204));
        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",employee);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}