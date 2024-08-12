package ott.j4jg_gateway.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId; // provider에서 제공하는 ID 값을 기본 키로 사용 (String 타입으로 변경)

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAddInfo userAddInfo;
}
