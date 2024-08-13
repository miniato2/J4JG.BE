package ott.j4jg_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import ott.j4jg_gateway.jwt.JWTFilter;
import ott.j4jg_gateway.oauth2.CustomLogoutSuccessHandler;
import ott.j4jg_gateway.oauth2.CustomSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTFilter jwtFilter;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String googleAuthorizationUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.google.user-name-attribute}")
    private String googleUserNameAttribute;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String kakaoAuthorizationUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    private String kakaoUserNameAttribute;

    @Autowired
    public SecurityConfig(CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomSuccessHandler customSuccessHandler, JWTFilter jwtFilter) {
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler(customSuccessHandler)
                        .clientRegistrationRepository(clientRegistrationRepository()))
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/**").permitAll()
                        .anyExchange().authenticated())
                .addFilterBefore(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler))
                .securityContextRepository(securityContextRepository());

        return http.build();
    }

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration googleClientRegistration = ClientRegistration.withRegistrationId("google")
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .redirectUri(googleRedirectUri)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("profile", "email")
                .authorizationUri(googleAuthorizationUri)
                .tokenUri(googleTokenUri)
                .userInfoUri(googleUserInfoUri)
                .userNameAttributeName(googleUserNameAttribute)
                .clientName("Google")
                .build();

        ClientRegistration kakaoClientRegistration = ClientRegistration.withRegistrationId("kakao")
                .clientId(kakaoClientId)
                .redirectUri(kakaoRedirectUri)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("account_email", "phone_number")
                .authorizationUri(kakaoAuthorizationUri)
                .tokenUri(kakaoTokenUri)
                .userInfoUri(kakaoUserInfoUri)
                .userNameAttributeName(kakaoUserNameAttribute)
                .clientName("Kakao")
                .build();

        return new InMemoryReactiveClientRegistrationRepository(googleClientRegistration, kakaoClientRegistration);
    }

    @Bean
    public WebSessionServerSecurityContextRepository securityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }
}
