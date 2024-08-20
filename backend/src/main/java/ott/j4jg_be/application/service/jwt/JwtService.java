package ott.j4jg_be.application.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ott.j4jg_be.domain.user.TokenInfo;
import ott.j4jg_be.domain.user.UserInfo;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final SecretKey secretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtService(@Value("${jwt.secret-key}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰의 유효성을 검증
    public boolean validateToken(String token) {
        try {
            logger.debug("Validating JWT token: {}", token);
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            logger.debug("JWT token is valid.");
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            logger.warn("JWT token validation failed: {}", token, e);
            return false;
        }
    }

    public TokenInfo getUserInfoFromToken(String token) {
        logger.debug("Parsing JWT token to extract user info: {}", token);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        // TokenInfo 클레임을 추출
        String tokenInfoJson = claims.get("TokenInfo", String.class);
        logger.debug("Extracted TokenInfo JSON: {}", tokenInfoJson);

        // TokenInfo가 없는 경우 예외 처리 또는 기본값 설정
        if (tokenInfoJson == null) {
            logger.warn("TokenInfo claim is missing in the JWT token");
            // 예외를 던지거나 기본 빈 객체를 반환
            return new TokenInfo();  // 또는 null 반환
        }

        try {
            TokenInfo tokenInfo = objectMapper.readValue(tokenInfoJson, TokenInfo.class);
            logger.debug("Converted TokenInfo JSON to object: {}", tokenInfo);
            return tokenInfo;
        } catch (JsonProcessingException e) {
            logger.error("Could not convert JSON to TokenInfo", e);
            throw new RuntimeException("Could not convert JSON to TokenInfo", e);
        }
    }

    // JWT 토큰 생성
    public String createToken(TokenInfo tokenInfo) {
        try {
            String tokenInfoJson = objectMapper.writeValueAsString(tokenInfo);

            return Jwts.builder()
                    .setSubject(tokenInfo.getUserId())
                    .claim("TokenInfo", tokenInfoJson)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 1시간 유효
                    .signWith(secretKey)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert TokenInfo to JSON", e);
        }
    }
}