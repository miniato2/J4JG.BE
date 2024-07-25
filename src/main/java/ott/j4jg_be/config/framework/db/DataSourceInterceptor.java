package ott.j4jg_be.config.framework.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceInterceptor {

    @Around("@annotation(ReadOnly) || @within(ReadOnly)")
    public Object setReadDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DataSourceContextHolder.useSlave();
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    @Around("@annotation(WriteOnly) || @within(WriteOnly)")
    public Object setWriteDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DataSourceContextHolder.useMaster();
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearDataSourceType();
        }
    }
}
