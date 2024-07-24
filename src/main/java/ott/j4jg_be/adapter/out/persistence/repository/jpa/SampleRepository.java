package ott.j4jg_be.adapter.out.persistence.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
    // Repository methods
}
