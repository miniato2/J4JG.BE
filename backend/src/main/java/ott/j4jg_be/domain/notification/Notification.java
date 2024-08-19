package ott.j4jg_be.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private String message;
    private int matchingId;
}
