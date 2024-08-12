package ott.j4jg_be.application.service.email;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.email.SendEmailUsecase;
import ott.j4jg_be.application.port.out.email.EmailServicePort;
import ott.j4jg_be.domain.email.Email;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService implements SendEmailUsecase {

    private final EmailServicePort emailServicePort;

    public EmailService(EmailServicePort emailServicePort) {
        this.emailServicePort = emailServicePort;
    }

    @Async
    @Override
    public CompletableFuture<Void> sendEmail(Email email) {
        return CompletableFuture.runAsync(() -> {
            emailServicePort.sendEmail(email);
        });
    }
}
