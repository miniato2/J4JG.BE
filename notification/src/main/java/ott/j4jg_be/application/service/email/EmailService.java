package ott.j4jg_be.application.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.email.SendEmailUsecase;
import ott.j4jg_be.application.port.out.email.EmailServicePort;
import ott.j4jg_be.domain.email.Email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService implements SendEmailUsecase {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final EmailServicePort emailServicePort;

    public EmailService(EmailServicePort emailServicePort) {
        this.emailServicePort = emailServicePort;
    }

    @Async
    @Override
    public CompletableFuture<Void> sendEmail(Email email) {
        return CompletableFuture.runAsync(() -> {
            String timestamp = LocalDateTime.now().format(formatter);
            try {
                emailServicePort.sendEmail(email);
                logger.info("[{}] Email sent successfully to: {}", timestamp, email.getRecipient());
            } catch (Exception e) {
                logger.error("[{}] Failed to send email to: {}", timestamp, email.getRecipient(), e);
                throw new RuntimeException("Email sending failed", e);
            }
        });
    }
}