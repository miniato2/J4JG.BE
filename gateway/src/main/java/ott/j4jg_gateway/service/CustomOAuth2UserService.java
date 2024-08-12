package ott.j4jg_gateway.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ott.j4jg_gateway.domain.dto.GoogleResponse;
import ott.j4jg_gateway.domain.dto.OAuth2Response;
import ott.j4jg_gateway.domain.dto.KakaoResponse;
import ott.j4jg_gateway.domain.enums.PROVIDER;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        // PROVIDER 값을 설정하고 각 제공자에 맞는 OAuth2Response 객체 생성
        if ("google".equals(registrationId)) {
            oAuth2Response = new GoogleResponse(PROVIDER.GOOGLE, oAuth2User.getAttributes());
        } else if ("kakao".equals(registrationId)) {
//            oAuth2Response = new KakaoResponse(PROVIDER.KAKAO, oAuth2User.getAttributes());
        } else {
            throw new OAuth2AuthenticationException("지원하지 않는 OAuth2 provider: " + registrationId);
        }

        return oAuth2User;
    }
}
