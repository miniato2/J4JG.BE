package ott.j4jg_be.config.framework.filter;


import org.springframework.core.MethodParameter;

import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.UserInfo;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @CurrentUser 어노테이션이 있고, 파라미터의 타입이 UserInfo인 경우에만 지원
        return parameter.getParameterAnnotation(CurrentUser.class) != null && parameter.getParameterType().equals(UserInfo.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        // ServerWebExchange에서 currentUserInfo 속성을 가져와 이를 Mono로 반환
        return Mono.justOrEmpty(exchange.getAttribute("currentUserInfo"));
    }
}
