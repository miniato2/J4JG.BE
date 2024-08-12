package ott.j4jg_gateway.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ott.j4jg_gateway.domain.entity.User;
import ott.j4jg_gateway.domain.entity.UserAddInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddInfoDTO {
    private String userNickname;
    private String surveyResponse;

    public static UserAddInfoDTO fromEntity(UserAddInfo userAddInfo) {
        if (userAddInfo == null) {
            return null;
        }
        return new UserAddInfoDTO(
                userAddInfo.getUserNickname(),
                userAddInfo.getSurveyResponse()
        );
    }

    public UserAddInfo toEntity(User user) {
        return UserAddInfo.builder()
                .user(user)
                .userNickname(this.userNickname)
                .surveyResponse(this.surveyResponse)
                .build();
    }
}
