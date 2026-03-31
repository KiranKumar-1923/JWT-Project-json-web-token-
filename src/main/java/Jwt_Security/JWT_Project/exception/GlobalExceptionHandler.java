package Jwt_Security.JWT_Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(
                "Error: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied() {
        return new ResponseEntity<>(
                "Access Denied",
                HttpStatus.FORBIDDEN
        );
    }
}