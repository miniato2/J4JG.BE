package ott.j4jg_gateway.domain.dto;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attributes; // Google 사용자 속성

    public GoogleResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google"; // 제공자 이름 반환
    }

    @Override
    public String getProviderId() {
        return attributes.get("sub").toString(); // Google의 사용자 ID 반환
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString(); // 사용자 이메일 반환
    }

    @Override
    public String getPhoneNumber() {
        return null; // Google API는 기본적으로 사용자 휴대전화번호를 제공하지 않음
    }
}
