package ott.j4jg_gateway.domain.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {
    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2User.class);

    private String provider;
    private String providerId;
    private String email;
    private String phoneNumber;

    public CustomOAuth2User(
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attributes,
            String provider,
            String providerId,
            String email,
            String phoneNumber,
            String userIdAttributeName) {
        super(authorities, attributes, userIdAttributeName);
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.phoneNumber = phoneNumber;

        logger.info("CustomOAuth2User created with provider: {}, providerId: {}, email: {}, phoneNumber: {}", provider, providerId, email, phoneNumber);

        validateAttributes();
    }

    private void validateAttributes() {
        if (this.provider == null) {
            logger.error("Provider is null");
        }
        if (this.providerId == null) {
            logger.error("ProviderId is null");
        }
        if (this.email == null) {
            logger.error("Email is null");
        }
        if (this.phoneNumber == null) {
            logger.warn("PhoneNumber is null");
            this.phoneNumber = "N/A";
        }
    }

    public String getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomOAuth2User{" +
                "provider='" + provider + '\'' +
                ", providerId='" + providerId + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
