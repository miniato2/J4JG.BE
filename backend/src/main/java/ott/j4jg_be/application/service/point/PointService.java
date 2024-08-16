package ott.j4jg_be.application.service.point;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.in.point.PointUseCase;
import ott.j4jg_be.application.port.out.point.PointHistoryPersistencePort;
import ott.j4jg_be.application.port.out.point.PointPersistencePort;
import ott.j4jg_be.common.e_num.PointTransactionType;
import ott.j4jg_be.domain.point.Point;

@Service
@RequiredArgsConstructor
public class PointService implements PointUseCase {

  private final PointPersistencePort pointPersistencePort;
  private final PointHistoryPersistencePort pointHistoryPersistencePort;

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void usePoints(String userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.USE);
  }

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void refundPoints(String userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.REFUND);
  }

  @Transactional(readOnly = false, rollbackFor = Throwable.class)
  @Override
  public void addPoints(String userId, int points, String reason) {
    executePointTransaction(userId, points, reason, PointTransactionType.ADD);
  }

  private void executePointTransaction(String userId, int points, String reason, PointTransactionType type) {
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
