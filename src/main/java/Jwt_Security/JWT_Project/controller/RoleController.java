package Jwt_Security.JWT_Project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @GetMapping("/user")
    public String userAccess() {
        return "Hello USER";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Hello ADMIN";
    }
}