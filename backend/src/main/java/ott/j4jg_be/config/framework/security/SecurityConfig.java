//package ott.j4jg_be.config.framework.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .formLogin(formLogin -> formLogin.disable())
//                .httpBasic(httpBasic -> httpBasic.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/**").permitAll()
//                );
//        return http.build();
//    }
//
////
////    // 권한 상하관계 설정
////    @Bean
////    public RoleHierarchy roleHierarchy() {
////        return roleHierarchy();
////    }
//
//
//}
