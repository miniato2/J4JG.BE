package ott.j4jg_gateway.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_gateway.model.dto.GoogleResponse;
import ott.j4jg_gateway.model.dto.KakaoResponse;
import ott.j4jg_gateway.model.dto.OAuth2Response;
import ott.j4jg_gateway.model.entity.RefreshToken;
import ott.j4jg_gateway.jwt.JWTUtil;
import ott.j4jg_gateway.repository.RedisRefreshTokenRepository;
import ott.j4jg_gateway.service.CustomOAuth2UserService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Collection;
import java.util.Map;

@Component
public class CustomSuccessHandler implements ServerAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    private final JWTUtil jwtUtil;
    private final RedisRefreshTokenRepository redisRefreshTokenRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    public CustomSuccessHandler(JWTUtil jwtUtil, RedisRefreshTokenRepository redisRefreshTokenRepository, CustomOAuth2UserService customOAuth2UserService) {
        this.jwtUtil = jwtUtil;
        this.redisRefreshTokenRepository = redisRefreshTokenRepository;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Override
    @Transactional
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        try {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oauth2User.getAttributes();
            logger.info("OAuth2User 속성: {}", attributes);

            OAuth2Response oAuth2Response = getOAuth2Response(oauth2User, authentication.getAuthorities());
            if (oAuth2Response == null) {
                throw new IllegalArgumentException("지원하지 않는 OAuth2 제공자입니다.");
            }

            customOAuth2UserService.saveOrUpdateUser(oAuth2Response);

            String email = oAuth2Response.getEmail();
            String provider = oAuth2Response.getProvider();
            String providerId = oAuth2Response.getProviderId();

            logger.info("사용자 인증 성공: email={}, provider={}, providerId={}", email, provider, providerId);

            String role = getRole(authentication);

            String accessToken = createAccessToken(email, role, provider, providerId);
            String refreshTokenValue = createRefreshToken(email, role, provider, providerId);

            saveRefreshTokenToRedis(refreshTokenValue, providerId, email, provider);

            setResponseHeaders(webFilterExchange, accessToken);

            // 리다이렉트 URL 설정
            URI redirectUri = URI.create("http://localhost:5173/");
            webFilterExchange.getExchange().getResponse().getHeaders().setLocation(redirectUri);
            webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.FOUND);
            return webFilterExchange.getExchange().getResponse().setComplete();

        } catch (Exception e) {
            logger.error("Authentication success 처리 중 오류 발생", e);
            return Mono.error(new RuntimeException("Authentication success 처리 중 오류 발생", e));
        }
    }

    private String getRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(() -> new IllegalArgumentException("Role is not found"));
    }

    private String createAccessToken(String email, String role, String provider, String providerId) {
        String token = jwtUtil.createJwt("access", email, role, provider, null, providerId, jwtUtil.getAccessTokenExpirationTime());
        logger.info("Access token 생성 성공");
        return token;
    }

    private String createRefreshToken(String email, String role, String provider, String providerId) {
        String token = jwtUtil.createJwt("refresh", email, role, provider, null, providerId, jwtUtil.getRefreshTokenExpirationTime());
        logger.info("Refresh token 생성 성공");
        return token;
    }

    private void saveRefreshTokenToRedis(String refreshTokenValue, String providerId, String email, String provider) {
        RefreshToken refreshToken = RefreshToken.builder()
                .providerId(providerId) // Redis에서 사용할 키는 providerId
                .token(refreshTokenValue)
                .userEmail(email)
                .provider(provider)
                .expiration(System.currentTimeMillis() + jwtUtil.getRefreshTokenExpirationTime())
                .build();
        redisRefreshTokenRepository.save(refreshToken).subscribe(); // Redis에 저장
    }

    private void setResponseHeaders(WebFilterExchange webFilterExchange, String accessToken) {
        webFilterExchange.getExchange().getResponse().addCookie(createCookie("accessToken", accessToken, (int) (jwtUtil.getAccessTokenExpirationTime() / 1000)));
        webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
    }

    private ResponseCookie createCookie(String name, String value, int maxAge) {
        return ResponseCookie.from(name, value)
                .path("/")
                .maxAge(Duration.ofSeconds(maxAge))
                .httpOnly(false)
                .secure(false)
                .build();
    }

    private OAuth2Response getOAuth2Response(OAuth2User oAuth2User, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if (attributes.containsKey("sub")) { // Google 속성
            return new GoogleResponse(attributes);
        } else if (attributes.containsKey("id")) { // Kakao 속성
            return new KakaoResponse(attributes);
        } else {
            return null;
        }
    }
}
