package ott.j4jg_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_gateway.domain.entity.UserAddInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddInfoRepository extends JpaRepository<UserAddInfo, String> {
}
