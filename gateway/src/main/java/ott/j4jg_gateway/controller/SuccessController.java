package ott.j4jg_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {
    @GetMapping("/good")
    public String successLogin() {
        return "success!";
    }
}
