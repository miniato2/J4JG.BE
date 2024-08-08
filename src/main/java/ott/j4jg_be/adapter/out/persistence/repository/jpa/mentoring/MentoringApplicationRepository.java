package ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;

import java.util.List;

public interface MentoringApplicationRepository extends JpaRepository<MentoringApplicationEntity, Integer> {
    List<MentoringApplicationEntity> findByStatus(boolean status);
}
