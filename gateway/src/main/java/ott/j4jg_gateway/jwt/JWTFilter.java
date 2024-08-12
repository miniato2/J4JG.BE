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
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 요청 속성에 클레임 정보 저장
                    exchange.getAttributes().put("provider", provider);
                    exchange.getAttributes().put("phoneNumber", phoneNumber);
                    exchange.getAttributes().put("providerId", providerId);
                }
            } catch (JwtException e) {
                // JWT 관련 예외 처리
                logger.warning("JWT Exception: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            } catch (Exception e) {
                // 기타 예외 처리
                logger.severe("Exception: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
}
