package ott.j4jg_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend_route", r -> r.path("/backend/**")
                        .filters(f -> f.rewritePath("/backend/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:5001"))  // 로컬 환경에서 실행 중인 백엔드 서비스의 주소
                .build();
    }
}
