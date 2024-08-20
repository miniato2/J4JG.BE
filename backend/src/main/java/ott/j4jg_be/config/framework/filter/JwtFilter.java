package ott.j4jg_be.config.framework.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ott.j4jg_be.application.service.jwt.JwtService;
import ott.j4jg_be.domain.user.TokenInfo;
import ott.j4jg_be.domain.user.UserInfo;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            logger.debug("Authorization header found, token: {}", token);  // 로그 추가

            if (jwtService.validateToken(token)) {
                TokenInfo tokenInfo = jwtService.getUserInfoFromToken(token);
                logger.debug("Token is valid. User info: {}", tokenInfo);  // 로그 추가
                request.setAttribute("TokenInfo", tokenInfo);
            } else {
                logger.warn("Invalid JWT token: {}", token);  // 로그 추가
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            if (authorizationHeader == null) {
                logger.warn("Authorization header is missing");  // 로그 추가
            } else {
                logger.warn("Authorization header does not start with 'Bearer '");  // 로그 추가
            }
        }

        filterChain.doFilter(request, response);
    }
}