package ott.j4jg_gateway.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RefreshToken")

public class RefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String providerId; // Redis의 키 역할을 하는 필드

    private String token; // 실제 토큰 값

    private String userEmail; // 사용자 이메일

    private String provider; // 제공자 정보

    private long expiration; // 만료 시간

}
