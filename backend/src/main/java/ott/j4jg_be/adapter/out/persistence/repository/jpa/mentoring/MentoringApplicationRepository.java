package ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;


public interface MentoringApplicationRepository extends JpaRepository<MentoringApplicationEntity, Integer> {

//    @Query("SELECT m FROM MentoringApplicationEntity m JOIN FETCH m.user u JOIN FETCH u.userAddInfo WHERE m.status = :status")
    Page<MentoringApplicationEntity> findByStatus(boolean b, Pageable pageable);

    MentoringApplicationEntity findByUserId(String userId);
}
