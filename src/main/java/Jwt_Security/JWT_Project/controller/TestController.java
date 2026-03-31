package Jwt_Security.JWT_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/encode")
    public String encode() {
        return encoder.encode("1234");
    }
}