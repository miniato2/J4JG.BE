package ott.j4jg_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_gateway.model.entity.User;
import ott.j4jg_gateway.model.entity.UserAddInfo;

import java.util.Optional;

public interface UserAddInfoRepository extends JpaRepository<UserAddInfo, String> {

    // User 엔티티를 사용하여 UserAddInfo를 찾는 메서드
    Optional<UserAddInfo> findByUser(User user);

    // 닉네임으로 UserAddInfo를 찾는 메서드
    Optional<UserAddInfo> findByUserNickname(String userNickname);
}
