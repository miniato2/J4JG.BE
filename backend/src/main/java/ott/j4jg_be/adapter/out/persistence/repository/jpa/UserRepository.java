package ott.j4jg_be.adapter.out.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
}
