package ott.j4jg_be.adapter.in.web.rest.email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.EmailDTO;
import ott.j4jg_be.adapter.in.web.mapper.EmailMapper;
import ott.j4jg_be.application.port.in.email.SendEmailUsecase;

@RestController
@RequestMapping("/test/email")
public class EmailController {

    private final SendEmailUsecase sendEmailUsecase;

    public EmailController(SendEmailUsecase sendEmailUsecase) {
        this.sendEmailUsecase = sendEmailUsecase;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDTO emailDTO) {

        sendEmailUsecase.sendEmail(EmailMapper.toDomail(emailDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
