package ott.j4jg_gateway.domain.dto;

import lombok.*;
import ott.j4jg_gateway.domain.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {
    private String userId;
    private String userEmail;
    private String provider;
    private String userPhoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String role;

    public static UserAuthDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserAuthDTO(
                user.getUserId(),
                user.getUserEmail(),
                user.getProvider(),
                user.getUserPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getRole()
        );
    }

}
