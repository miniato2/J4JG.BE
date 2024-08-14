package ott.j4jg_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_gateway.model.entity.RefreshToken;
import ott.j4jg_gateway.jwt.JWTUtil;
import ott.j4jg_gateway.repository.RedisRefreshTokenRepository;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RedisRefreshTokenRepository refreshRepository;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    @Autowired
    public ReissueController(JWTUtil jwtUtil, RedisRefreshTokenRepository refreshRepository,
                             @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
                             @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    @PostMapping("/reissue")
    public Mono<ResponseEntity<String>> reissue(@RequestHeader("Authorization") String authorizationHeader) {
        // Authorization 헤더에서 "Bearer " 제거
        String refreshToken = authorizationHeader.startsWith("Bearer ") ?
                authorizationHeader.substring(7) : authorizationHeader;

        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새로고친 토큰이 없습니다."));
        }

        // 새로고친 토큰 검증
        if (jwtUtil.isExpired(refreshToken)) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새로고친 토큰이 만료되었습니다."));
        }

        // 새로고친 토큰 존재 확인
        String providerId = jwtUtil.getProviderId(refreshToken);
        return refreshRepository.findById(providerId)
                .flatMap(existingToken -> {
                    // 토큰에서 사용자 정보 추출
                    String userEmail = jwtUtil.getEmail(refreshToken);
                    String role = jwtUtil.getRole(refreshToken);
                    String provider = jwtUtil.getProvider(refreshToken);

                    // 새로운 액세스 토큰 생성
                    String newAccessToken = jwtUtil.createJwt("access", userEmail, role, provider, "", providerId, accessTokenExpirationTime);

                    // 새로운 리프레시 토큰 생성
                    String newRefreshToken = jwtUtil.createJwt("refresh", userEmail, role, provider, "", providerId, refreshTokenExpirationTime);

                    // 새로고친 토큰 저장 및 기존 토큰 삭제
                    return refreshRepository.deleteByProviderId(providerId)
                            .then(saveNewRefreshToken(providerId, newRefreshToken, userEmail, provider))
                            .then(Mono.defer(() -> {
                                // 응답 설정
                                ResponseCookie accessCookie = createCookie("accessToken", newAccessToken, accessTokenExpirationTime);
                                ResponseCookie refreshCookie = createCookie("refreshToken", newRefreshToken, refreshTokenExpirationTime);
                                return Mono.just(
                                        ResponseEntity.ok()
                                                .header("Set-Cookie", accessCookie.toString())
                                                .header("Set-Cookie", refreshCookie.toString())
                                                .body("토큰이 재발급되었습니다.")
                                );
                            }));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 새로고친 토큰입니다.")));
    }

    private Mono<Void> saveNewRefreshToken(String providerId, String refreshToken, String userEmail, String provider) {
        Date expirationDate = new Date(System.currentTimeMillis() + refreshTokenExpirationTime);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .providerId(providerId)  // id로 사용
                .token(refreshToken)  // token
                .userEmail(userEmail)  // userEmail 설정
                .provider(provider) // provider 설정
                .expiration(expirationDate.getTime())  // expiration
                .build();
        return refreshRepository.save(refreshTokenEntity)
                .doOnError(e -> {
                    System.err.println("Error saving refresh token to Redis: " + e.getMessage());
                })
                .then();
    }

    private ResponseCookie createCookie(String name, String value, long expirationTimeMs) {
        return ResponseCookie.from(name, value)
                .maxAge(Duration.ofMillis(expirationTimeMs)) // Refresh token 만료 시간
                .httpOnly(true) // 보안을 위해 HTTP 전용 설정
                .secure(true) // HTTPS 환경에서만 쿠키 전송
                .path("/") // 쿠키가 유효한 경로 설정
                .build();
    }
}
