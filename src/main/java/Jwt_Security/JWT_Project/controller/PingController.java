package Jwt_Security.JWT_Project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}