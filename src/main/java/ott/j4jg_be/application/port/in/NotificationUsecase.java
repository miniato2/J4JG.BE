package ott.j4jg_be.application.port.in;

public interface NotificationUsecase {

    void sendNotification(String phoneNumber, String message);
}
