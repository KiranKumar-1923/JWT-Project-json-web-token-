package Jwt_Security.JWT_Project.controller;

import Jwt_Security.JWT_Project.model.LoginRequest;
import Jwt_Security.JWT_Project.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    // ✅ Use constructor injection instead of @Autowired
    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        System.out.println("LOGIN API HIT");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        try {
            Authentication authentication =
                    authManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            )
                    );

            if (authentication.isAuthenticated()) {
                String token = jwtUtil.generateToken(request.getUsername());
                return ResponseEntity.ok(token);
            }

        } catch (Exception e) {
            System.out.println("AUTH FAILED: " + e.getMessage()); // ← watch this
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Auth failed: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}