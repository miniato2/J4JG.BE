package ott.j4jg_gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_gateway.domain.dto.CustomOAuth2User;
import ott.j4jg_gateway.domain.dto.GoogleResponse;
import ott.j4jg_gateway.domain.dto.KakaoResponse;
import ott.j4jg_gateway.domain.dto.OAuth2Response;
import ott.j4jg_gateway.domain.entity.User;
import ott.j4jg_gateway.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        logger.info("OAuth2 사용자 요청 로드: {}", userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        logger.info("OAuth2 사용자 세부 정보: {}", oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        logger.info("제공자 등록 ID: {}", registrationId);

        OAuth2Response oAuth2Response = getOAuth2Response(oAuth2User, registrationId);
        if (oAuth2Response == null) {
            logger.error("지원하지 않는 OAuth2 제공자: {}", registrationId);
            throw new OAuth2AuthenticationException("지원하지 않는 OAuth2 제공자: " + registrationId);
        }

        User user;
        try {
            user = saveOrUpdateUser(oAuth2Response);
            logger.info("저장/업데이트된 사용자 정보: {}", user);
        } catch (Exception e) {
            logger.error("사용자 저장 또는 업데이트 중 오류 발생: ", e);
            throw new RuntimeException("사용자 저장 실패", e);
        }

        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole())
        );

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(
                authorities,
                oAuth2User.getAttributes(),
                oAuth2Response.getProvider(),
                user.getUserId(),
                oAuth2Response.getEmail(),
                oAuth2Response.getPhoneNumber(),
                getUserIdAttributeName(registrationId)
        );

        logger.info("Custom OAuth2 사용자: {}", customOAuth2User);

        return customOAuth2User;
    }

    public User saveOrUpdateUser(OAuth2Response oAuth2Response) {
        logger.info("saveOrUpdateUser 메서드 시작");

        Optional<User> optionalUser = userRepository.findByUserEmailAndProvider(oAuth2Response.getEmail(), oAuth2Response.getProvider());

        User user = optionalUser.map(existingUser -> {
            existingUser.setUserPhoneNumber(oAuth2Response.getPhoneNumber() != null ? oAuth2Response.getPhoneNumber() : "");
            existingUser.setUpdatedAt(LocalDateTime.now());
            logger.info("기존 사용자 업데이트: {}", existingUser);
            return existingUser;
        }).orElseGet(() -> {
            User newUser = User.builder()
                    .userId(oAuth2Response.getProviderId())
                    .userEmail(oAuth2Response.getEmail())
                    .userPhoneNumber(oAuth2Response.getPhoneNumber() != null ? oAuth2Response.getPhoneNumber() : "")
                    .provider(oAuth2Response.getProvider())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .role("ROLE_MENTEE")
                    .build();
            logger.info("새 사용자 생성: {}", newUser);
            return newUser;
        });

        User savedUser = userRepository.save(user);
        logger.info("DB에 저장된 사용자: {}", savedUser);
        return savedUser;
    }

    private OAuth2Response getOAuth2Response(OAuth2User oAuth2User, String registrationId) {
        if ("kakao".equals(registrationId)) {
            return new KakaoResponse(oAuth2User.getAttributes());
        } else if ("google".equals(registrationId)) {
            return new GoogleResponse(oAuth2User.getAttributes());
        }
        logger.error("지원하지 않는 OAuth2 제공자: {}", registrationId);
        return null;
    }

    private String getUserIdAttributeName(String registrationId) {
        switch (registrationId.toLowerCase()) {
            case "google":
                return "sub";
            case "kakao":
                return "id";
            default:
                throw new IllegalArgumentException("지원하지 않는 OAuth2 제공자: " + registrationId);
        }
    }
}
