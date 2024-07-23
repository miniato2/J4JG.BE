package ott.j4jg_be.adapter.out.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ott.j4jg_be.adapter.out.persistence.entity.SampleEntity;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
    // Repository methods
}
