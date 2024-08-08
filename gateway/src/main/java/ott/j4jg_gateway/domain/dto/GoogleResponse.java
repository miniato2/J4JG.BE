package ott.j4jg_gateway.domain.dto;

import ott.j4jg_gateway.domain.enums.PROVIDER;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

    private PROVIDER provider;
    private final Map<String, Object> attribute;

    public GoogleResponse(PROVIDER provider, Map<String, Object> attribute) {
        this.provider = provider;
        this.attribute = attribute;
    }

    @Override
    public PROVIDER getProvider() {
        return provider;
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }
}
