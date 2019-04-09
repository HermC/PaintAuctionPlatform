package edu.nju.ise.auction.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Jwt工具类
 *
 * @author HermC yzy627@126.com
 * @version 1.0
 * @date 2019/01/04
 * @time 20:33
 */
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 生成token
     *
     * @param username 用户名
     * @return token字符串
     */
    public String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + expiration);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username", username)    // 附带username信息
                    .withExpiresAt(date)    // 到期时间
                    .sign(algorithm);    // 加密算法
            return tokenHead + token;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token    token字符串
     * @param username 用户名
     * @return true token正确，false token不正确
     */
    public boolean verify(String token, String username) {
        if (token == null || token.length() <= tokenHead.length()) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(this.cutHead(token));
            return true;
        } catch (JWTVerificationException | UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取token中的信息，无需secret解密也能获得
     *
     * @param token token字符串
     * @return username用户名
     */
    public String getUsername(String token) {
        if (token == null || token.length() <= tokenHead.length()) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(this.cutHead(token));
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验时间是否超时
     *
     * @param token token字符串
     * @return true超时，false未超时
     */
    public boolean verifyTimeout(String token) {
        if (token == null || token.length() <= tokenHead.length()) {
            return false;
        }
        try {
            DecodedJWT jwt = JWT.decode(this.cutHead(token));
            Date expiration = jwt.getExpiresAt();
            Date now = new Date();
            return now.after(expiration);
        } catch (JWTDecodeException e) {
            logger.error(e.getMessage());
            return true;
        }
    }

    /**
     * 减去token头部部分
     *
     * @param token token字符串
     * @return 减去后的部分
     */
    private String cutHead(String token) {
        return token.substring(tokenHead.length());
    }

}
