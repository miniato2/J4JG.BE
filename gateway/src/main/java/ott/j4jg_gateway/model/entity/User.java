package ott.j4jg_gateway.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ott.j4jg_gateway.model.converter.PhoneNumberEncryptor;
import ott.j4jg_gateway.model.enums.USERROLE;

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
    private String userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Convert(converter = PhoneNumberEncryptor.class)
    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private USERROLE role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAddInfo userAddInfo;
}
