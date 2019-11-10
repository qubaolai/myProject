package com.qubaolai.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qubaolai.po.Employee;
import org.apache.commons.lang.StringUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qubaolai
 * @date 2019/10/30 21:03
 */
public class JWTUtil {
    public static Map<String, String> decrypt(String param) {
        Map<String, String> result = new HashMap<>();
        String userId = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                DecodedJWT decode = JWT.decode(param);
                userId = decode.getAudience().get(0);
                Map<String, Claim> claims = decode.getClaims();
                Claim ceshi1111 = claims.get("date");
                String dateString = ceshi1111.asString();
                result.put("empNum", userId);
                result.put("date", dateString);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }
        return result;
    }


    public static String encrypt(Employee employee) {
        String convert = new Date().toString();
        String token = "";
        try {
            token = JWT.create().withAudience(employee.getEmployeeNumber())
                    .withClaim("date", convert)
                    .withClaim(employee.getEmployeeNumber(),employee.getEmployeeNumber())
                    .sign(Algorithm.HMAC256(MD5Tools.string2MD5(employee.getPassword()) + convert));

        } catch (Exception e) {
            System.out.println(11111);
        }
        return token;
    }
}
