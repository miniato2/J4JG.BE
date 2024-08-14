//package ott.j4jg_gateway.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import ott.j4jg_gateway.domain.entity.User;
//import ott.j4jg_gateway.repository.UserRepository;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class CustomOAuth2UserServiceTest {
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private DefaultOAuth2UserService defaultOAuth2UserService;
//
//    @Autowired
//    private CustomOAuth2UserService customOAuth2UserService;
//
//    @Captor
//    private ArgumentCaptor<User> userCaptor;
//
//    private ClientRegistration googleClientRegistration;
//    private ClientRegistration kakaoClientRegistration;
//
//    @BeforeEach
//    public void setUp() {
//        googleClientRegistration = ClientRegistration.withRegistrationId("google")
//                .clientId("google-client-id")
//                .clientSecret("google-client-secret")
//                .redirectUri("http://localhost:8080/login/oauth2/code/google")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope("profile", "email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//                .tokenUri("https://oauth2.googleapis.com/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName("sub")
//                .clientName("Google")
//                .build();
//
//        kakaoClientRegistration = ClientRegistration.withRegistrationId("kakao")
//                .clientId("kakao-client-id")
//                .redirectUri("http://localhost:8080/login/oauth2/code/kakao")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope("account_email", "phone_number")
//                .authorizationUri("https://kauth.kakao.com/oauth/authorize")
//                .tokenUri("https://kauth.kakao.com/oauth/token")
//                .userInfoUri("https://kapi.kakao.com/v2/user/me")
//                .userNameAttributeName("id")
//                .clientName("Kakao")
//                .build();
//    }
//
//    @Test
//    @DisplayName("구글 사용자 정보 로드 테스트")
//    public void 구글_사용자_정보_로드_테스트() throws OAuth2AuthenticationException {
//        // given
//        OAuth2UserRequest userRequest = new OAuth2UserRequest(googleClientRegistration, mock(OAuth2AccessToken.class));
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("sub", "googleId");
//        attributes.put("email", "test@gmail.com");
//        OAuth2User oAuth2User = mock(OAuth2User.class);
//        when(oAuth2User.getAttributes()).thenReturn(attributes);
//
//        when(defaultOAuth2UserService.loadUser(any(OAuth2UserRequest.class))).thenReturn(oAuth2User);
//        when(userRepository.findByUserEmailAndProvider("test@gmail.com", "google")).thenReturn(Optional.empty());
//
//        // when
//        OAuth2User loadedUser = customOAuth2UserService.loadUser(userRequest);
//
//        // then
//        assertThat(loadedUser).isNotNull();
//        assertThat(loadedUser.getAttributes()).containsEntry("sub", "googleId");
//
//        verify(userRepository, times(1)).save(userCaptor.capture());
//        User savedUser = userCaptor.getValue();
//        assertThat(savedUser.getUserId()).isEqualTo("googleId");
//        assertThat(savedUser.getUserEmail()).isEqualTo("test@gmail.com");
//        assertThat(savedUser.getProvider()).isEqualTo("google");
//    }
//
//    @Test
//    @DisplayName("카카오 사용자 정보 로드 테스트")
//    public void 카카오_사용자_정보_로드_테스트() throws OAuth2AuthenticationException {
//        // given
//        OAuth2UserRequest userRequest = new OAuth2UserRequest(kakaoClientRegistration, mock(OAuth2AccessToken.class));
//
//        Map<String, Object> kakaoAccount = new HashMap<>();
//        kakaoAccount.put("email", "test@kakao.com");
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("id", "kakaoId");
//        attributes.put("kakao_account", kakaoAccount);
//        OAuth2User oAuth2User = mock(OAuth2User.class);
//        when(oAuth2User.getAttributes()).thenReturn(attributes);
//
//        when(defaultOAuth2UserService.loadUser(any(OAuth2UserRequest.class))).thenReturn(oAuth2User);
//        when(userRepository.findByUserEmailAndProvider("test@kakao.com", "kakao")).thenReturn(Optional.empty());
//
//        // when
//        OAuth2User loadedUser = customOAuth2UserService.loadUser(userRequest);
//
//        // then
//        assertThat(loadedUser).isNotNull();
//        assertThat(loadedUser.getAttributes()).containsEntry("id", "kakaoId");
//
//        verify(userRepository, times(1)).save(userCaptor.capture());
//        User savedUser = userCaptor.getValue();
//        assertThat(savedUser.getUserId()).isEqualTo("kakaoId");
//        assertThat(savedUser.getUserEmail()).isEqualTo("test@kakao.com");
//        assertThat(savedUser.getProvider()).isEqualTo("kakao");
//    }
//
//    @Test
//    @DisplayName("지원하지 않는 제공자로 사용자 정보 로드 시 예외 발생 테스트")
//    public void 지원하지_않는_제공자로_사용자_정보_로드_시_예외_발생_테스트() {
//        // given
//        ClientRegistration unsupportedClientRegistration = ClientRegistration.withRegistrationId("unsupported")
//                .clientId("unsupported-client-id")
//                .clientSecret("unsupported-client-secret")
//                .redirectUri("http://localhost:8080/login/oauth2/code/unsupported")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope("profile", "email")
//                .authorizationUri("https://unsupported.com/oauth/authorize")
//                .tokenUri("https://unsupported.com/oauth/token")
//                .userInfoUri("https://unsupported.com/userinfo")
//                .userNameAttributeName("id")
//                .clientName("Unsupported")
//                .build();
//
//        OAuth2UserRequest userRequest = new OAuth2UserRequest(unsupportedClientRegistration, mock(OAuth2AccessToken.class));
//
//        // when
//        OAuth2AuthenticationException exception = assertThrows(OAuth2AuthenticationException.class,
//                () -> customOAuth2UserService.loadUser(userRequest));
//
//        // then
//        assertThat(exception.getMessage()).contains("지원하지 않는 OAuth2 제공자");
//    }
//}
