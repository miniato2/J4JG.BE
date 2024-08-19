package ott.j4jg_be.application.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ott.j4jg_be.domain.user.TokenInfo;
import ott.j4jg_be.domain.user.UserInfo;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // JWT 토큰의 유효성을 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT에서 UserInfo 객체를 추출
    public TokenInfo getUserInfoFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String tokenInfoJson = claims.get("TokenInfo", String.class);
        try {
            return objectMapper.readValue(tokenInfoJson, TokenInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert JSON to UserInfo", e);
        }
    }

    public String createToken(TokenInfo tokenInfo) {
        try {
            String tokenInfoJson = objectMapper.writeValueAsString(tokenInfo);

            return Jwts.builder()
                    .setSubject(tokenInfo.getUserId())
                    .claim("TokenInfo", tokenInfoJson)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert UserInfo to JSON", e);
        }
    }
}
