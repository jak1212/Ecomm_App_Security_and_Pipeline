package TestConfiguration;

import com.example.demo.model.persistence.AppUser;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class TestUserConfig {
    @Bean
    public CommandLineRunner setupUser(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("testuser").toString() != null) {
                repo.save(new AppUser("testuser", new Cart(), encoder.encode("password"), "ROLE_USER"));
            }
        };
    }
}

