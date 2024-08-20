package ott.j4jg_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GatewayConfiguration.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend_route", r -> r.path("/backend/**")
                        .filters(f -> f.rewritePath("/backend/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:5001"))  // 로컬 환경에서 실행 중인 백엔드 서비스의 주소
                .build();
    }

    public GatewayFilter logFilter() {
        return (exchange, chain) -> {
            logger.info("Request URI: {}", exchange.getRequest().getURI());
            logger.info("Request Headers: {}", exchange.getRequest().getHeaders());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Response Status Code: {}", exchange.getResponse().getStatusCode());
            }));
        };
    }


}
