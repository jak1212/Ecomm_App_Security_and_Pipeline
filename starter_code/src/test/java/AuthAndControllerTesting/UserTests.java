package AuthAndControllerTesting;


import com.example.demo.model.persistence.AppUser;
import com.example.demo.model.persistence.AuthResponse;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.AuthRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = com.example.demo.SareetaApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages = {"com.example.demo"})
public class UserTests {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    private int port;
    public static int testUserCounter = 0;
    public static String authToken;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders authHeaders;

    public AppUser testUser;




    @BeforeAll
    public void userSetup() {
        // Setup user credentials
        String newUsername = "testuser";
        String newUserPassword = "abcd";
        String hashedPassword = passwordEncoder.encode(newUserPassword);
        Cart cart = new Cart();
        String role = "ROLE_USER";
        testUser = new AppUser(newUsername, cart, hashedPassword, role);
        userRepository.save(testUser);
        AppUser savedUser = userRepository.findByUsername("testuser");
        System.out.println("Saved user in DB: " + savedUser.getPassword());
        testUserCounter++;

        // Create login request payload
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(newUsername);
        authRequest.setPassword(newUserPassword);

        // Send POST /login
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> loginRequest = new HttpEntity<>(authRequest, headers);
        String loginUrl = "http://localhost:" + port + "/auth/login";

        try {
            ResponseEntity<AuthResponse> loginResponse = template.exchange(
                    loginUrl,
                    HttpMethod.POST,
                    loginRequest,
                    AuthResponse.class
            );

            UserTests.authToken = loginResponse.getBody().getToken();
            System.out.println("Token: " + UserTests.authToken);

            // You can store the token if needed for further authenticated tests
            // e.g., this.jwtToken = jwtToken;

        } catch (HttpClientErrorException e) {
            System.err.println("Login failed: " + e.getStatusCode());
            System.err.println("Body: " + e.getResponseBodyAsString());
        }
    }

    @BeforeEach
    public void setHeaders() {
        authHeaders = new HttpHeaders();
        authHeaders.setContentType(MediaType.APPLICATION_JSON);
        authHeaders.setBearerAuth(UserTests.authToken);
    }

    @Test
    public void getUser(){

        HttpEntity<Void> entity = new HttpEntity<>(authHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/user/" + testUser.getUsername(),
                HttpMethod.GET,
                entity,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    }
