package com.nongboo.flowery.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;

    public String createJWT(int userId){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365))) // 발급날짜 계산
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY) // signature 부분 --> 암호키 지정 가능
                .compact();
    }

    public String getJWT(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS_TOKEN");
    }

    public int getUserId() throws Exception{
        String accessToken = getJWT();
        if(accessToken == null || accessToken.length() == 0){
            throw new Exception("JWT가 비어있음");
        }

        Jws<Claims> claims;

        try{
            claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);

        }catch (Exception exception){
            throw new Exception("JWT 오류");
        }

        return claims.getBody().get("userId", Integer.class);
    }
}
