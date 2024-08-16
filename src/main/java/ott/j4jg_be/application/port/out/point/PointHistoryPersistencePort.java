package ott.j4jg_be.application.port.out.point;

import ott.j4jg_be.domain.point.PointHistory;

public interface PointHistoryPersistencePort {
  void saveHistory(String userId, PointHistory pointHistory);
}
