package ott.j4jg_be.application.port.out.email;

import ott.j4jg_be.domain.email.Email;

import java.util.concurrent.CompletableFuture;

public interface EmailServicePort {
    void sendEmail(Email email);
}