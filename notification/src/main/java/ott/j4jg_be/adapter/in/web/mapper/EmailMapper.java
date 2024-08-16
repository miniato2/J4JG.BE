package ott.j4jg_be.adapter.in.web.mapper;

import ott.j4jg_be.adapter.in.web.dto.EmailDTO;
import ott.j4jg_be.domain.email.Email;

public class EmailMapper {

    public static Email toDomain(EmailDTO emailDTO) {
        return new Email(emailDTO.getRecipient(), emailDTO.getSubject(), emailDTO.getBody());
    }

    public static EmailDTO toDTO(Email email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(email.getRecipient());
        emailDTO.setSubject(email.getSubject());
        emailDTO.setBody(email.getBody());
        return emailDTO;
    }
}
