package ott.j4jg_be.config.framework.db;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Aspect
@Order(0)
@Component
@Slf4j
public class DataSourceInterceptor {

    @Around("@within(org.springframework.transaction.annotation.Transactional) || @annotation(org.springframework.transaction.annotation.Transactional)")
    public Object setDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        Transactional transactional = getTransactionalAnnotation(joinPoint);

        if (transactional != null) {
            if (transactional.readOnly()) {
                DataSourceContextHolder.setDataSourceType("SLAVE");
                log.info("Using SLAVE data source for read-only transaction on method: {}", joinPoint.getSignature());
            } else {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                Method method = methodSignature.getMethod();

                if (isReadOperation(method) && !transactional.readOnly()) {
                    throw new IllegalStateException("읽기 작업에서 readOnly=false로 설정된 트랜잭션이 사용되었습니다.");
                }
                log.info("Using MASTER data source for non-read-only transaction on method: {}", joinPoint.getSignature());
                DataSourceContextHolder.setDataSourceType("MASTER");
            }
        } else {
            log.info("Using MASTER data source transaction on method: {}", joinPoint.getSignature());
            DataSourceContextHolder.setDataSourceType("MASTER");
        }

        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    private Transactional getTransactionalAnnotation(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (method.isAnnotationPresent(Transactional.class)) {
            return method.getAnnotation(Transactional.class);
        } else {
            return joinPoint.getTarget().getClass().getAnnotation(Transactional.class);
        }
    }

    private boolean isReadOperation(Method method) {
        String methodName = method.getName();
        return methodName.startsWith("get") || methodName.startsWith("find") || methodName.startsWith("search");
    }
}
