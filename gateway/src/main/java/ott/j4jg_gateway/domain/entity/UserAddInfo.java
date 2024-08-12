package ott.j4jg_gateway.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_add_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserAddInfo {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "survey_response")
    private String surveyResponse;
}
