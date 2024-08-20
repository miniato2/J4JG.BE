package ott.j4jg_be.application.port.in.sns;

public interface NotificationUsecase {

    public void sendSMSNotification(String phoneNumber, String message);
}
