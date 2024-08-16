package ott.j4jg_be.adapter.out.persistence.adapter.jpa.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.PointHistoryEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.PointHistoryEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.PointHistoryRepository;
import ott.j4jg_be.application.port.out.point.PointHistoryPersistencePort;
import ott.j4jg_be.domain.point.PointHistory;

@Service
@RequiredArgsConstructor
public class PointHistoryAdapter implements PointHistoryPersistencePort {

  private final PointHistoryRepository pointHistoryRepository;
  private final PointHistoryEntityMapper pointHistoryEntityMapper;

  public void saveHistory(String userId, PointHistory pointHistory) {
    PointHistoryEntity entity = pointHistoryEntityMapper.toEntity(userId, pointHistory);
    pointHistoryRepository.save(entity);
  }
}
