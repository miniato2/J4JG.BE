package ott.j4jg_be.adapter.in.web.rest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;
import ott.j4jg_be.application.port.in.test.SampleUseCase;
import ott.j4jg_be.application.service.jwt.JwtService;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.UserInfo;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleUseCase sampleUseCase;
    private final JwtService jwtService;

    @GetMapping("/samples")
    public List<SampleDTO> getSamples() {
        return sampleUseCase.getSamples();
    }

    @PostMapping("/samples")
    public SampleEntity saveSample(@RequestBody SampleDTO sampleDTO) {
        return sampleUseCase.saveSample(sampleDTO);
    }

    @GetMapping("/sample/user")
    public String getUserProfile(@CurrentUser UserInfo user) {
        return "User profile for: " + user.getUsername() + " with email: " + user.getEmail();
    }

    @GetMapping("/sample/createJwt")
    public String getUserProfile() {
        // 예제 UserInfo 객체 생성
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        userInfo.setUsername("johndoe");
        userInfo.setEmail("john.doe@example.com");

        // JWT 토큰 생성
        String token = jwtService.createToken(userInfo);

        return "Generated JWT token: " + token;
    }

}
