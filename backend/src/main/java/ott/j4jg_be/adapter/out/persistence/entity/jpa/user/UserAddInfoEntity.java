package ott.j4jg_be.adapter.out.persistence.entity.jpa.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_add_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserAddInfoEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Column(name = "survey_response")
    private String surveyResponse;
}