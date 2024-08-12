package ott.j4jg_gateway.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import ott.j4jg_gateway.domain.entity.RefreshToken;
import reactor.core.publisher.Mono;

@Repository
public class RedisRefreshTokenRepository {

    @Autowired
    private ReactiveRedisTemplate<String, RefreshToken> redisTemplate;

    // Provider ID로 Refresh Token을 찾는 메서드
    public Mono<RefreshToken> findByProviderId(String providerId) {
        return redisTemplate.opsForValue().get(providerId);
    }

    // Provider ID로 Refresh Token을 삭제하는 메서드
    public Mono<Void> deleteByProviderId(String providerId) {
        return redisTemplate.opsForValue().delete(providerId).then();
    }

    // Refresh Token을 Redis에 저장하는 메서드
    public Mono<RefreshToken> save(RefreshToken refreshToken) {
        return redisTemplate.opsForValue().set(refreshToken.getId(), refreshToken).thenReturn(refreshToken);
    }
}
