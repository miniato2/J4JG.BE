package ott.j4jg_be.adapter.in.web.rest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.application.port.in.test.SampleUseCase;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;
import ott.j4jg_be.application.service.jwt.JwtService;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.TokenInfo;
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
    public String getUserProfile(@CurrentUser TokenInfo tokenInfo) {
        return "User profile for: " + tokenInfo.getUserId() + " with email: " + tokenInfo.getRole();
    }

    @GetMapping("/sample/createJwt")
    public String getUserProfile(String userId, String role) {
        // 예제 UserInfo 객체 생성
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setRole(role);

        // JWT 토큰 생성
        String token = jwtService.createToken(tokenInfo);

        return "Generated JWT token: " + token;
    }

}
