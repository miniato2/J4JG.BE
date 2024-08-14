package ott.j4jg_gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ott.j4jg_gateway.model.entity.User;
import ott.j4jg_gateway.model.entity.UserAddInfo;
import ott.j4jg_gateway.model.enums.USERROLE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddInfoDTO {
    private String userId;
    private String userEmail;
    private String provider;
    private String userPhoneNumber;
    private USERROLE role;
    private String userNickname;
    private String surveyResponse;

    public static UserAddInfoDTO fromEntity(User user, UserAddInfo userAddInfo) {
        if (user == null || userAddInfo == null) {
            return null;
        }
        return new UserAddInfoDTO(
                user.getUserId(),
                user.getUserEmail(),
                user.getProvider(),
                user.getUserPhoneNumber(),
                user.getRole(),
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
