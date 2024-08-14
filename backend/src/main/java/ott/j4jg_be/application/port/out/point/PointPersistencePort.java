package ott.j4jg_be.application.port.out.point;

import ott.j4jg_be.domain.point.Point;

import java.util.Optional;

public interface PointPersistencePort {
  Optional<Point> findByUserId(String userId);
  void save(String userId, Point point);
  void deleteByUserId(String userId);
}
