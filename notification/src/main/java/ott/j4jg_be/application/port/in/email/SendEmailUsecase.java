package ott.j4jg_be.application.port.in.email;

import ott.j4jg_be.domain.email.Email;

public interface SendEmailUsecase {
    void sendEmail(Email email);
}
