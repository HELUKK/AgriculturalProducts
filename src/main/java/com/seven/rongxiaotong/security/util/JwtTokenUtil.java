package com.seven.rongxiaotong.security.util;

import com.seven.rongxiaotong.security.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
     * 生成令牌，验证等等一些操作
     */
    @Data
//@ConfigurationProperties(prefix = "jwt")
    @Component
    public class JwtTokenUtil {
        private String secret = "qst123456";
        // 过期时间 毫秒
        private static final Long expiration = 1000*60*60*24L;
        private static final String header = "Authorization";

        /**
         * 从数据声明生成令牌
         *
         * @param claims 数据声明
         * @return 令牌
         */
        private String generateToken(Map<String, Object> claims) {
            Date expirationDate = new Date(System.currentTimeMillis() + expiration);
            return Jwts.builder()
//                    令牌类型
                    .setHeaderParam("type","JWT")
//                    签名算法
                    .setHeaderParam("alg","HS512")
//                    令牌主题
                    .setSubject("USER")
//                    私有字段
                    .setClaims(claims)
//                    设置过期时间
                    .setExpiration(expirationDate)
//                    签名哈希
                    .signWith(SignatureAlgorithm.HS512, secret)
//                    转换为字符串
                    .compact();
        }

        public static String getHeader() {
            return header;
        }

        /**
         * 从令牌中获取数据声明
         *
         * @param token 令牌
         * @return 数据声明
         */
        private Claims getClaimsFromToken(String token) {
            Claims claims;
            try {
                claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            } catch (Exception e) {
                claims = null;
            }
            return claims;
        }

        /**
         * 生成令牌
         *
         * @param userDetails 用户
         * @return 令牌
         */
        public String generateToken(UserDetails userDetails) {
            JwtUser jwtUser= (JwtUser) userDetails;
            Map<String, Object> claims = new HashMap<>(2);
            claims.put(Claims.SUBJECT, userDetails.getUsername());
            claims.put(Claims.ISSUED_AT, new Date());
            claims.put("username", jwtUser.getUsername());
            claims.put("role",jwtUser.getAuthorities());
            return generateToken(claims);
        }

        /**
         * 从令牌中获取用户名
         *
         * @param token 令牌
         * @return 用户名
         */
        public String getUsernameFromToken(String token) {
            String username;
            try {
                Claims claims = getClaimsFromToken(token);
                username = claims.getSubject();
            } catch (Exception e) {
                System.out.println("令牌存在但无法读取，有可能是令牌过期或被篡改");
                username = null;
            }
            return username;
        }

        /**
         * 判断令牌是否过期
         *
         * @param token 令牌
         * @return 是否过期
         */
        public Boolean isTokenExpired(String token) {
            try {
                Claims claims = getClaimsFromToken(token);
                Date expiration = claims.getExpiration();
                return expiration.before(new Date());
            } catch (Exception e) {
                return true;
            }
        }

        /**
         * 刷新令牌
         *
         * @param token 原令牌
         * @return 新令牌
         */
        public String refreshToken(String token) {
            String refreshedToken;
            try {
                Claims claims = getClaimsFromToken(token);
                claims.put(Claims.ISSUED_AT, new Date());
                refreshedToken = generateToken(claims);
            } catch (Exception e) {
                refreshedToken = null;
            }
            return refreshedToken;
        }

        /**
         * 验证令牌
         *
         * @param token       令牌
         * @param userDetails 用户
         * @return 是否有效
         */
        public Boolean validateToken(String token, UserDetails userDetails) {
            JwtUser user = (JwtUser) userDetails;
            String username = getUsernameFromToken(token);
            return (username.equals(user.getUsername()) && !isTokenExpired(token));
        }
    }
