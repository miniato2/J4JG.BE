package ott.j4jg_be.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.in.PointUseCase;
import ott.j4jg_be.application.port.out.PointHistoryPersistencePort;
import ott.j4jg_be.application.port.out.PointPersistencePort;
import ott.j4jg_be.common.e_num.PointTransactionType;
import ott.j4jg_be.domain.Point;

@Service
@RequiredArgsConstructor
public class PointService implements PointUseCase {

  private final PointPersistencePort pointPersistencePort;
  private final PointHistoryPersistencePort pointHistoryPersistencePort;

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void usePoints(Long userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.USE);
  }

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void refundPoints(Long userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.REFUND);
  }

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void addPoints(Long userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.ADD);
  }

  private void executePointTransaction(Long userId, int points, String reason, PointTransactionType type) {
    Point point = pointPersistencePort.findByUserId(userId)
        .orElseThrow(() -> new EntityNotFoundException("User points not found"));

    switch (type) {
      case USE:
        point.usePoints(points, reason);
        break;
      case REFUND:
        point.refundPoints(points, reason);
        break;
      case ADD:
        point.addPoints(points, reason);
        break;
    }

    pointHistoryPersistencePort.saveHistory(userId, point.getCurrentHistory());
    pointPersistencePort.save(userId, point);
  }
}
