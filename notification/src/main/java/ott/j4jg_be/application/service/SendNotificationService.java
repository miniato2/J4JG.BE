package ott.j4jg_be.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.in.SendNotificationUsecase;
import ott.j4jg_be.application.port.in.email.SendEmailUsecase;
import ott.j4jg_be.application.port.in.sns.NotificationUsecase;
import ott.j4jg_be.application.port.out.NotificationPort;
import ott.j4jg_be.domain.Notification;
import ott.j4jg_be.domain.email.Email;

import java.util.List;

@Service
public class SendNotificationService implements SendNotificationUsecase {

    private final NotificationUsecase notificationUsecase;
    private final SendEmailUsecase sendEmailUsecase;
    private final NotificationPort notificationPort;
    public SendNotificationService(NotificationUsecase notificationUsecase,
                                   SendEmailUsecase sendEmailUsecase,
                                   NotificationPort notificationPort){
        this.sendEmailUsecase = sendEmailUsecase;
        this.notificationUsecase = notificationUsecase;
        this.notificationPort = notificationPort;

    }


    @Override
    public void sendNotification() {
        List<Notification> notificationList = notificationPort.getNotificationList();

        notificationList.forEach(
                notification -> {
                    System.out.println(notification.toString());
                    String message = notification.getUserName() + "님 멘토링이 매칭이 완료되었습니다. 지금 바로 확인해 보세요";
                    //문자보내기
                    notificationUsecase.sendSMSNotification(notification.getPhone(), message);
                    //이메일보내기
                    sendEmailUsecase.sendEmail(new Email(notification.getEmail(), "잡포자기 매칭완료", message));
                    //status true로 update
                    notificationPort.updateNotification(notification.getNotificationId());
                }
        );
    }
}
