package ott.j4jg_be.adapter.out.persistence.adapter.jpa;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.out.PointPersistencePort;
import ott.j4jg_be.domain.Point;

@Service
@RequiredArgsConstructor
public class PointPersistenceAdapter implements PointPersistencePort {

  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public Optional<Point> findByUserId(Long userId) {
    String key = "points:" + userId;
    Point point = (Point) redisTemplate.opsForValue().get(key);
    return Optional.ofNullable(point);
  }

  public Optional<Point> findTempByUserId(Long userId) {
    String key = "points:temp:" + userId;
    Point point = (Point) redisTemplate.opsForValue().get(key);
    return Optional.ofNullable(point);
  }

  @Override
  public void save(Long userId, Point point) {
    // 임시 저장
    String tempKey = "points:temp:" + userId;
    redisTemplate.opsForValue().set(tempKey, point, 10, TimeUnit.MINUTES);

    // 최종 저장
    try {
      String finalKey = "points:" + userId;
      redisTemplate.opsForValue().set(finalKey, point, 1, TimeUnit.HOURS);
      redisTemplate.delete(tempKey); // 임시 저장소에서 삭제
    } catch (Exception e) {
      redisTemplate.delete(tempKey); // 임시 저장소에서 삭제
      throw e;
    }
  }

  @Override
  public void deleteByUserId(Long userId) {
    String key = "points:" + userId;
    redisTemplate.delete(key);
  }


//  @Cacheable(value = "points", key = "#userId")
//  @Override
//  public Optional<Point> findByUserId(Long userId) {
//    Optional<UserEntity> userEntity = userRepository.findById(userId);
//    if (userEntity.isPresent()) {
//      Optional<PointEntity> pointEntity = pointRepository.findByUser(userEntity.get());
//      return pointEntity.map(pointEntityMapper::mapToDomain);
//    }
//    return Optional.empty();
//  }
//
//  @CacheEvict(value = "points", key = "#userId")
//  @Override
//  public void save(Long userId, Point point) {
//    UserEntity userEntity = userRepository.findById(userId)
//        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    PointEntity pointEntity = pointEntityMapper.mapToEntity(point, userEntity);
//    pointRepository.save(pointEntity);
//  }
}
