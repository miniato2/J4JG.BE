package ott.j4jg_gateway.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import ott.j4jg_gateway.domain.entity.RefreshToken;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class RedisRefreshTokenRepository {

    @Autowired
    private ReactiveRedisTemplate<String, RefreshToken> redisTemplate;

    public Mono<RefreshToken> save(RefreshToken refreshToken) {
        return redisTemplate.opsForValue()
                .set(refreshToken.getProviderId(), refreshToken)
                .thenReturn(refreshToken)
                .doOnSuccess(token -> redisTemplate.expire(refreshToken.getProviderId(), Duration.ofMillis(refreshToken.getExpiration() - System.currentTimeMillis())).subscribe());
    }

    public Mono<RefreshToken> findById(String providerId) {
        return redisTemplate.opsForValue().get(providerId);
    }

    public Mono<Boolean> deleteById(String providerId) {
        return redisTemplate.opsForValue().delete(providerId);
    }

    public Mono<Boolean> deleteByProviderId(String providerId) {
        return redisTemplate.opsForValue().delete(providerId);
    }
}
