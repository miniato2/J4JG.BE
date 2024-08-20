package ott.j4jg_be.adapter.in;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ott.j4jg_be.application.port.in.SendNotificationUsecase;

@Component
public class NotificationScheduler {

    private final SendNotificationUsecase sendNotificationUsecase;

    public NotificationScheduler(SendNotificationUsecase sendNotificationUsecase){
        this.sendNotificationUsecase = sendNotificationUsecase;
    }

    @Scheduled(fixedDelay = 60000)
    public void notificationScheduler(){
        sendNotificationUsecase.sendNotification();
    }
}
