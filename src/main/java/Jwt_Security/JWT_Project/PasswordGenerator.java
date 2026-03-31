package Jwt_Security.JWT_Project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGenerator {

    @Bean
    CommandLineRunner generate(PasswordEncoder encoder) {
        return args -> {
            System.out.println("HASH FOR 1234 = " + encoder.encode("1234"));
        };
    }
}