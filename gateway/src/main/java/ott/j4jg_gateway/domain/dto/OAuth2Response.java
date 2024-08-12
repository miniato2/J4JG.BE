package ott.j4jg_gateway.domain.dto;


public interface OAuth2Response {

    // 제공자 (kakao, google 등)
    String getProvider();

    // 제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    // 이메일
    String getEmail();

    // 사용자 휴대전화번호
    String getPhoneNumber();

}
