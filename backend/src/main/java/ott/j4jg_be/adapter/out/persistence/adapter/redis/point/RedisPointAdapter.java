package ott.j4jg_be.adapter.out.persistence.adapter.redis.point;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.out.point.PointPersistencePort;
import ott.j4jg_be.domain.point.Point;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisPointAdapter implements PointPersistencePort {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public Optional<Point> findByUserId(Long userId) {
    String key = "points:" + userId;
    Object pointValue = redisTemplate.opsForValue().get(key);
    Point point = objectMapper.convertValue(pointValue, Point.class);
    return Optional.ofNullable(point);
  }

  @Transactional
  @Override
  public void save(Long userId, Point point) {
    String key = "points:" + userId;
    redisTemplate.opsForValue().set(key, point);
  }

  @Transactional
  @Override
  public void deleteByUserId(Long userId) {
    String key = "points:" + userId;
    redisTemplate.delete(key);
  }
}
