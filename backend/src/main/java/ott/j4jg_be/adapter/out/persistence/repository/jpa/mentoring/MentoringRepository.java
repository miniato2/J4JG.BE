package ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;


public interface MentoringRepository extends JpaRepository<MentoringEntity, Integer> {

    Page<MentoringEntity> findAllByOrderByUpdatedAtDesc(Pageable pageable);

    Page<MentoringEntity> findByUserId(String userId, Pageable pageable);

    Page<MentoringEntity> findByUserIdAndStatus(String userId, boolean status, Pageable pageable);
}
