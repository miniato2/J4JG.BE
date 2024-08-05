package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.NotificationUsecase;
import ott.j4jg_be.application.port.out.SnsPort;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationUsecase {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final SnsPort snsPort;

    @Override
    public void sendSMSNotification(String phoneNumber, String message) {
        logger.info("Sending SMS to : {} / Content : {}", phoneNumber, message); 
        try {
            snsPort.publish(phoneNumber, message);
            logger.info("SMS successfully sent to {}", phoneNumber);
        } catch (Exception e) {
            logger.error("Failed to send SMS to {}: {}", phoneNumber, e.getMessage());
        }
    }
}
