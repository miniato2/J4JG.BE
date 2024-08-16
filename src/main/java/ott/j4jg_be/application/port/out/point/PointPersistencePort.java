package ott.j4jg_be.application.port.out.point;

import java.util.Optional;
import ott.j4jg_be.domain.point.Point;

public interface PointPersistencePort {
  Optional<Point> findByUserId(String userId);
  void save(String userId, Point point);
  void deleteByUserId(String userId);
}
