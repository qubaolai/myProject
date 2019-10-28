package com.guanghua.pojo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.guanghua.po.Employee;

import java.io.UnsupportedEncodingException;

public class EmployeeExtend {
    private String token;

    public String getToken(Employee employee) {
        // + DateUtil.convert2String(new Date(), "yyyyMMddHHmmss")
            token = JWT.create().withAudience(employee.getId())
                    .sign(Algorithm.HMAC256(employee.getPassword() + employee.getDeviceid()));
        return token;
    }
}
