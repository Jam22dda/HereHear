package com.ssafy.herehear.global.util;

import com.ssafy.herehear.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {


    @Value("${auth.secretKey}")
    private String SECRET_KEY;


//    private static final Long ACCESS_TOEKN_VALIDATE_TIME = 1000L * 60 * 30;
    private static final Long ACCESS_TOEKN_VALIDATE_TIME = 1000L * 60 * 60 * 24;
    private static final Long REFRESH_TOKEN_VALIDATE_TIME = 1000L * 60 * 60 * 24 * 365;

    public String createAccessToken(Member member) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + ACCESS_TOEKN_VALIDATE_TIME);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("memberId", Long.toString(member.getMemberId()));
        payloads.put("provider", member.getProvider());

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setClaims(payloads)
                .setSubject("auth")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String createRefreshToken() {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + REFRESH_TOKEN_VALIDATE_TIME);
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject("refresh")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validatateToken(String token) {
        try{
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) { // 유효하지 않은 jwt
            throw new MalformedJwtException("jwt not valid");
        } catch (ExpiredJwtException e) { // 만료된 JWT
            throw new ExpiredJwtException(null, null, "expired");
        } catch (UnsupportedJwtException e) { // 지원하지 않는 JWT
            throw new UnsupportedJwtException("unsupported jwt");
        } catch (IllegalArgumentException e) { // 빈 값
            throw new IllegalArgumentException("empty jwt");
        }
    }

    public boolean isExpired(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Long getClaimFromToken(String token, String name) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong((String)claims.get(name));
    }

    public Long getClaimFromExpirationToken(String expirationToken, String name) {
        try{
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(expirationToken)
                    .getBody();
            return Long.parseLong((String)claims.get(name));
        } catch (ExpiredJwtException e) {
            return Long.parseLong((String)e.getClaims().get(name));
        }
    }

    public String getAccessToken(HttpServletRequest request) {
        log.info("getaccesstoken까지");
        String accessToken = request.getHeader("Authorization");
        log.info("get 함수 안 token: {}", accessToken);
        accessToken = accessToken.replace("Bearer ", "");
        log.info("여기도 안 들어오면서 왜... 애 안 와");
        return accessToken;
    }
}
