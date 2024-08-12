package ott.j4jg_be.application.port.out.point;

import ott.j4jg_be.domain.point.Point;

import java.util.Optional;

public interface PointPersistencePort {
  Optional<Point> findByUserId(Long userId);
  void save(Long userId, Point point);
  void deleteByUserId(Long userId);
}
