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

    @Async("taskExecutor")
    @Override
    public CompletableFuture<String> sendEmail(Email email) {
        return CompletableFuture.supplyAsync(() -> {
            String startTime = LocalDateTime.now().format(formatter);
            System.out.println("Start sending email: " + startTime);

            String timestamp = LocalDateTime.now().format(formatter);
            try {
                emailServicePort.sendEmail(email);
                String successTime = LocalDateTime.now().format(formatter);
                System.out.println("Email sent: " + successTime);
                logger.info("[{}] Email sent successfully to: {}", timestamp, email.getRecipient());
                return "Email sent successfully to: " + email.getRecipient() + " at " + successTime;
            } catch (Exception e) {
                String failureTime = LocalDateTime.now().format(formatter);
                System.out.println("Failed to send email: " + failureTime);
                logger.error("[{}] Failed to send email to: {}", timestamp, email.getRecipient(), e);
                throw new RuntimeException("Email sending failed", e);
            } finally {
                String endTime = LocalDateTime.now().format(formatter);
                System.out.println("End of email process: " + endTime);
            }
        });
    }
}