package ott.j4jg_be.application.port.out;

import ott.j4jg_be.domain.PointHistory;

public interface PointHistoryPersistencePort {
  void saveHistory(Long userId, PointHistory pointHistory);
}
