package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.NotificationUsecase;
import ott.j4jg_be.application.port.out.SnsPort;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationUsecase {

    private final SnsPort snsPort;


    @Override
    public void sendSMSNotification(String phoneNumber, String message) {
        snsPort.publish(phoneNumber, message);
    }
}
