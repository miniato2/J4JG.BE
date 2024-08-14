package ott.j4jg_be.adapter.out.persistence.adapter.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.PointHistoryEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.PointHistoryEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.PointHistoryRepository;
import ott.j4jg_be.application.port.out.PointHistoryPersistencePort;
import ott.j4jg_be.domain.PointHistory;

@Service
@RequiredArgsConstructor
public class PointHistoryAdapter implements PointHistoryPersistencePort {

  private final PointHistoryRepository pointHistoryRepository;
  private final PointHistoryEntityMapper pointHistoryEntityMapper;

  public void saveHistory(Long userId, PointHistory pointHistory) {
    PointHistoryEntity entity = pointHistoryEntityMapper.toEntity(userId, pointHistory);
    pointHistoryRepository.save(entity);
  }
}
