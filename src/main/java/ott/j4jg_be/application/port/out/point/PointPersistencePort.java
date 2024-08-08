package ott.j4jg_be.application.port.out.point;

import java.util.Optional;
import ott.j4jg_be.domain.point.Point;

public interface PointPersistencePort {
  Optional<Point> findByUserId(Long userId);
  void save(Long userId, Point point);
  void deleteByUserId(Long userId);
}
