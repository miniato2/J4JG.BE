package ott.j4jg_be.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInfo {
    private String userId;
    private String role;
}
