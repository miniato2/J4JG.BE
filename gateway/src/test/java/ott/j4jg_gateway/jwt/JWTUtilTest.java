//package ott.j4jg_gateway.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//class JWTUtilTest {
//
//    @Value("${spring.jwt.secret}")
//    private String secret;
//
//    @Value("${spring.jwt.accessTokenExpirationTime}")
//    private long accessTokenExpirationTime;
//
//    @Value("${spring.jwt.refreshTokenExpirationTime}")
//    private long refreshTokenExpirationTime;
//
//    private JWTUtil jwtUtil;
//
//    @BeforeEach
//    void setUp() {
//        jwtUtil = new JWTUtil(secret, accessTokenExpirationTime, refreshTokenExpirationTime);
//    }
//
//    @Test
//    @DisplayName("JWT 생성 및 검증 테스트")
//    void JWT_생성_및_검증() {
//        // given
//        String email = "test@example.com";
//        String role = "ROLE_USER";
//        String provider = "google";
//        String phoneNumber = "1234567890";
//        String providerId = "provider123";
//
//        // when
//        String token = jwtUtil.createJwt("access", email, role, provider, phoneNumber, providerId, jwtUtil.getAccessTokenExpirationTime());
//
//        // then
//        assertNotNull(token);
//        assertFalse(jwtUtil.isExpired(token));
//
//        Claims claims = jwtUtil.getAllClaimsFromToken(token);
//        assertEquals(email, claims.get("email"));
//        assertEquals(role, claims.get("role"));
//        assertEquals(provider, claims.get("provider"));
//        assertEquals(phoneNumber, claims.get("phone_number"));
//        assertEquals(providerId, claims.get("provider_id"));
//
//        Date expiration = claims.getExpiration();
//        assertTrue(expiration.after(new Date()));
//        assertTrue(expiration.before(new Date(System.currentTimeMillis() + jwtUtil.getAccessTokenExpirationTime() + 1000)));
//    }
//
//    @Test
//    @DisplayName("JWT 토큰 만료 테스트")
//    void JWT_토큰_만료() {
//        // given
//        String email = "test@example.com";
//        String role = "ROLE_USER";
//        String provider = "google";
//        String phoneNumber = "1234567890";
//        String providerId = "provider123";
//
//        // when
//        String token = jwtUtil.createJwt("access", email, role, provider, phoneNumber, providerId, 1000);
//
//        // then
//        assertNotNull(token);
//        assertFalse(jwtUtil.isExpired(token));
//
//        // 만료 확인을 위해 2초 대기
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        assertThrows(ExpiredJwtException.class, () -> {
//            jwtUtil.getAllClaimsFromToken(token);
//        });
//    }
//}
