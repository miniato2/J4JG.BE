package ott.j4jg_be.adapter.out.persistence.adapter.redis;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.out.PointPersistencePort;
import ott.j4jg_be.domain.Point;

@Service
@RequiredArgsConstructor
public class RedisPointAdapter implements PointPersistencePort {

  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public Optional<Point> findByUserId(Long userId) {
    String key = "points:" + userId;
    Point point = (Point) redisTemplate.opsForValue().get(key);
    return Optional.ofNullable(point);
  }

  @Transactional
  @Override
  public void save(Long userId, Point point) {
    String key = "points:" + userId;
    redisTemplate.opsForValue().set(key, point, 1, TimeUnit.HOURS); // 최종 저장소에 저장
  }

  @Transactional
  @Override
  public void deleteByUserId(Long userId) {
    String key = "points:" + userId;
    redisTemplate.delete(key);
  }
}
