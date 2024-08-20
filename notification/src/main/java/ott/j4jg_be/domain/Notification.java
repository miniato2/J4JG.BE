package ott.j4jg_be.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private int notificationId;
    private String userName;
    private String email;
    private String phone;
}
