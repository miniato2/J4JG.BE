package ott.j4jg_be.application.port.out;

import java.util.Optional;
import ott.j4jg_be.domain.Point;

public interface PointPersistencePort {
  Optional<Point> findByUserId(Long userId);
  void save(Long userId, Point point);
  void deleteByUserId(Long userId);
}
