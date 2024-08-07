package ott.j4jg_be.application.service.email;

import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.email.SendEmailUsecase;
import ott.j4jg_be.application.port.out.email.EmailServicePort;
import ott.j4jg_be.domain.email.Email;

@Service
public class EmailService implements SendEmailUsecase {

    private final EmailServicePort emailServicePort;

    public EmailService(EmailServicePort emailServicePort) {
        this.emailServicePort = emailServicePort;
    }

    @Override
    public void sendEmail(Email email) {

        emailServicePort.sendEmail(email);

    }
}
