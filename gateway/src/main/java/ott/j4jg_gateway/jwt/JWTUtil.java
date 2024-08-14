package ott.j4jg_gateway.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;
    private static final Logger logger = Logger.getLogger(JWTUtil.class.getName());

    public JWTUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
                   @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    // 직접 정의한 getter 메서드
    public long getAccessTokenExpirationTime() {
        return accessTokenExpirationTime;
    }

    public long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }

    // 토큰에서 모든 클레임을 추출하는 메서드
    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.warning("토큰이 만료되었습니다: " + e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.warning("지원되지 않는 JWT입니다: " + e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            logger.warning("잘못된 JWT 형식입니다: " + e.getMessage());
            throw e;
        } catch (SignatureException e) {
            logger.warning("유효하지 않은 서명입니다: " + e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.warning("잘못된 클레임입니다: " + e.getMessage());
            throw e;
        } catch (JwtException e) {
            logger.warning("JWT 예외 발생: " + e.getMessage());
            throw e;
        }
    }

    // 토큰에서 클레임을 파싱하는 메서드
    public Jws<Claims> parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            logger.warning("JWT 예외 발생: " + e.getMessage());
            throw e;
        }
    }

    // 권한을 리스트로 반환하는 메서드
    public List<GrantedAuthority> getAuthorities(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

    // 토큰에서 이메일을 추출하는 메서드
    public String getEmail(String token) {
        return getAllClaimsFromToken(token).get("email", String.class);
    }

    // 토큰에서 제공자를 추출하는 메서드
    public String getProvider(String token) {
        return getAllClaimsFromToken(token).get("provider", String.class);
    }

    // 토큰에서 역할을 추출하는 메서드
    public String getRole(String token) {
        return getAllClaimsFromToken(token).get("role", String.class);
    }

    // 토큰의 만료 여부를 확인하는 메서드
    public boolean isExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    // JWT를 생성하는 메서드
    public String createJwt(String category, String email, String role, String provider, String phoneNumber, String providerId, long expiredMs) { // providerId를 String으로 변경
        return Jwts.builder()
                .claim("category", category)
                .claim("email", email)
                .claim("role", role)
                .claim("provider", provider)
                .claim("phone_number", phoneNumber)
                .claim("provider_id", providerId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    // 토큰의 유효성을 검사하는 메서드
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaims(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            logger.warning("토큰이 만료되었습니다: " + e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            logger.warning("지원되지 않는 JWT입니다: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            logger.warning("잘못된 JWT 형식입니다: " + e.getMessage());
            return false;
        } catch (SignatureException e) {
            logger.warning("유효하지 않은 서명입니다: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.warning("잘못된 클레임입니다: " + e.getMessage());
            return false;
        } catch (JwtException e) {
            logger.warning("JWT 예외 발생: " + e.getMessage());
            return false;
        }
    }
}