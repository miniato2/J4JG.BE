package ott.j4jg_be.adapter.out.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.ScrapEntity;

public interface ScrapRepository extends JpaRepository<ScrapEntity, Integer> {

    ScrapEntity findByUserIdAndJobInfoId(Long userId, int jobInfoId);
}
