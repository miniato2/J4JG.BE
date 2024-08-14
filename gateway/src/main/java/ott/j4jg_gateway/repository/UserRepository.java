package ott.j4jg_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_gateway.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserEmailAndProvider(String userEmail, String provider);

    // userId로 사용자 조회
    Optional<User> findById(String userId);
}
