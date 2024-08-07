package ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;

import java.util.List;

public interface MentoringRepository extends JpaRepository<MentoringEntity, Integer> {

    List<MentoringEntity> findAllByOrderByUpdatedAtDesc();
}
