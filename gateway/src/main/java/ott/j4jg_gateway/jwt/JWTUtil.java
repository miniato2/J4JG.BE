package ott.j4jg_gateway.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
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
    @Getter
    private final long accessTokenExpirationTime;
    @Getter
    private final long refreshTokenExpirationTime;
    private static final Logger logger = Logger.getLogger(JWTUtil.class.getName());

    public JWTUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
                   @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            logger.warning("JWT 예외 발생: " + e.getMessage());
            throw e;
        }
    }

    public String getProviderId(String token) {
        return getAllClaimsFromToken(token).get("provider_id", String.class);
    }

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

    public List<GrantedAuthority> getAuthorities(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

    public String getEmail(String token) {
        return getAllClaimsFromToken(token).get("email", String.class);
    }

    public String getProvider(String token) {
        return getAllClaimsFromToken(token).get("provider", String.class);
    }

    public String getRole(String token) {
        return getAllClaimsFromToken(token).get("role", String.class);
    }

    public boolean isExpired(String token) {
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public String createJwt(String category, String email, String role, String provider, String phoneNumber, String providerId, long expiredMs) {
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

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaims(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            logger.warning("JWT 예외 발생: " + e.getMessage());
            return false;
        }
    }
}
