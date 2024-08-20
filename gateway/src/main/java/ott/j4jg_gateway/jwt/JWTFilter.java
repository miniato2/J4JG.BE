package ott.j4jg_gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;
import java.util.logging.Logger;

@Component
public class JWTFilter implements WebFilter {

    private final JWTUtil jwtUtil;
    private static final Logger logger = Logger.getLogger(JWTFilter.class.getName());

    @Autowired
    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        String requestUri = exchange.getRequest().getURI().getPath();

        Predicate<String> isLoginOrOauth2Path = uri ->
                uri.matches("^/backend/(?:/.*)?$");

        if (isLoginOrOauth2Path.test(requestUri)) {
            return chain.filter(exchange);
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7);

            try {

                // 토큰 유효성 검사 및 클레임 추출
                if (jwtUtil.validateToken(accessToken)) {
                    Jws<Claims> claimsJws = jwtUtil.parseClaims(accessToken);
                    Claims claims = claimsJws.getBody();

                    String email = claims.get("email", String.class);
                    String role = claims.get("role", String.class);
                    String provider = claims.get("provider", String.class);
                    String phoneNumber = claims.get("phone_number", String.class);
                    String providerId = claims.get("provider_id", String.class);

                    // 인증 객체 설정
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, null, jwtUtil.getAuthorities(role));

                    // 기존 컨텍스트 지우기
                    SecurityContextHolder.clearContext();
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // null 값이 아닌 경우에만 요청 속성에 클레임 정보 저장
                    if (provider != null) {
                        exchange.getAttributes().put("provider", provider);
                    }
                    if (phoneNumber != null) {
                        exchange.getAttributes().put("phoneNumber", phoneNumber);
                    }
                    if (providerId != null) {
                        exchange.getAttributes().put("providerId", providerId);
                    }
                }
            } catch (JwtException e) {

                System.out.println("JWT Catch");

                // JWT 관련 예외 처리
                logger.warning("JWT 예외 발생: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            } catch (Exception e) {

                System.out.println("Exception Catch");
                // 기타 예외 처리
                logger.severe("기타 예외 발생: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
}
