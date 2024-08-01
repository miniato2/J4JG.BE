package ott.j4jg_be.adapter.in.web.sns;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.NotificationUsecase;



@RestController
@RequiredArgsConstructor
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationUsecase notificationUseCase;

    @GetMapping("/test/notification")
    public String sendTestNotification() {
        String phoneNumber = "+821098743010";
        String message = "Test Notification! 문자는 잘 오는 것 같습니다";

        logger.info("Sending notification to {}: {}", phoneNumber, message);

        notificationUseCase.sendNotification(phoneNumber, message);

        logger.info("Notification sent to {}", phoneNumber);

        return "Notification sent!";
    }
}
