package com.qubaolai.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qubaolai.common.exception.exceptions.NoTokenException;
import com.qubaolai.po.Employee;
import com.qubaolai.vo.ResultVo;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: casespace-server
 * @Package: com.thunisoft.casespace.common.utils
 * @ClassName: JWTUtil
 * @Author: shichuanqi
 * @Description: Jwt解密工具类
 * @Date: 2019/9/2 16:30
 * @Version: 1.0
 */
public class JWTUtil {
    /** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
    public static final String SECRET = "JKKLJOoasdlfj";
    /** token 过期时间: 10天 */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 15;

    /**
     * JWT生成Token.<br/>
     *
     * JWT构成: header, payload, signature
     *
     *            登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(Employee employee) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP").withClaim("empNum", employee.getEmployeeNumber())
                .withClaim("password",employee.getPassword())
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, String> verifyToken(String token) {
        DecodedJWT jwt = null;
        Map<String, String> result = new HashMap<>();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim user_id_claim = claims.get("empNum");
            Claim password = claims.get("password");
            if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
                // token 校验失败, 抛出Token验证非法异常
                throw new NoTokenException("token");
            }
            if (null == password || StringUtils.isEmpty(password.asString())) {
                // token 校验失败, 抛出Token验证非法异常
                throw new NoTokenException("token");
            }
            result.put("empNum", user_id_claim.asString());
            result.put("password", password.asString());
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return result;
    }

    /**
     * 根据Token获取user_id
     *
     * @return user_id
     */
//    public static Boolean getAppUID(String token) {
//        Map<String, Claim> claims = verifyToken(token);
//        Claim user_id_claim = claims.get("empNum");
//        Claim password = claims.get("password");
//        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
//            // token 校验失败, 抛出Token验证非法异常
//            throw new NoTokenException("token");
//        }
//        if (null == password || StringUtils.isEmpty(password.asString())) {
//            // token 校验失败, 抛出Token验证非法异常
//            throw new NoTokenException("token");
//        }
//        return true;
//    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setEmployeeNumber("asd");
        employee.setPassword("asd");
        String token = JWTUtil.createToken(employee);
        System.out.println(token);

        Map<String, String> stringStringMap = JWTUtil.verifyToken(token);
        stringStringMap.get("empNum");
        stringStringMap.get("password");
        System.out.println(1);
    }
}
