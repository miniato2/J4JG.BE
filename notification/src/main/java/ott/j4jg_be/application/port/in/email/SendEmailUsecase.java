package ott.j4jg_be.application.port.in.email;

import ott.j4jg_be.domain.email.Email;

import java.util.concurrent.CompletableFuture;

public interface SendEmailUsecase {
    CompletableFuture<String> sendEmail(Email email);
}
