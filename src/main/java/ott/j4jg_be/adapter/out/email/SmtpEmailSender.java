package ott.j4jg_be.adapter.out.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ott.j4jg_be.application.port.out.email.EmailServicePort;
import ott.j4jg_be.domain.email.Email;

@Component
public class SmtpEmailSender implements EmailServicePort {

    private final JavaMailSender mailSender;

    public SmtpEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email.getRecipient());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true);

            mailSender.send(message);

            System.out.println("Successed to send Email");
        } catch (MessagingException e) {

            System.out.println("Failed to send email");
            e.printStackTrace();
        }
    }
}
