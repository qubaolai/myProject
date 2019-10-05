package com.guanghua.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guanghua.po.Employee;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    public static final long EXPIRATION_TIME = 3600_000_000L; // 1000 hour
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    /**
     *  解密
     * @param param
     * @return
     */
    public static Map<String, String> decrypt(String param) {
        Map<String, String> result = new HashMap<>();
        String userId = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                DecodedJWT decode = JWT.decode(param);
                userId = decode.getAudience().get(0);
                Map<String, Claim> claims = decode.getClaims();
                Claim ceshi1111 = claims.get(userId);
                String dateString = ceshi1111.asString();
                result.put("userId", userId);
                result.put("date", dateString);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }
        return result;
    }


    /**
     * 生成token
     * @param employee
     * @return
     */
    public static String encrypt(Employee employee) {
        /**
         * 当前时间时间戳
         */
        String convert = DateUtil.convert(new Date());
        String token = "";
        // + DateUtil.convert2String(new Date(), "yyyyMMddHHmmss")
        token = JWT.create().withAudience(employee.getId()).withClaim(employee.getId(), convert)
                .sign(Algorithm.HMAC256(employee.getPassword() + employee.getDeviceid() + convert));
        return token;
    }

    public static void main(String[] args) {
        Employee tUser = new Employee();
        tUser.setId("ceshi1111");
        tUser.setPassword("ceshi1111");
        String encrypt = JWTUtil.encrypt(tUser);
        System.out.println(encrypt);
        Map<String, String> decrypt = JWTUtil.decrypt(encrypt);

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ceshi1111" + decrypt.get("date"))).build();
        jwtVerifier.verify(encrypt);
        System.out.println("111");
    }
}
