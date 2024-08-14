package ott.j4jg_gateway.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@RedisHash("RefreshToken")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("providerId")  // JSON에서 'providerId'로 직렬화/역직렬화
    private String providerId; // Redis의 키 역할을 하는 필드

    @JsonProperty("token")
    private String token; // 실제 토큰 값

    @JsonProperty("userEmail")
    private String userEmail; // 사용자 이메일

    @JsonProperty("provider")
    private String provider; // 제공자 정보

    @JsonProperty("expiration")
    private long expiration; // 만료 시간

}
