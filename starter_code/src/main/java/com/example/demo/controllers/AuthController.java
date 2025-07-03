package com.example.demo.controllers;

import com.example.demo.model.persistence.AuthResponse;
import com.example.demo.model.requests.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public JwtService jwtService;

    @Autowired
    public AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        System.out.println(">>> Inside login controller");
try {
    Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
    );
    String token = jwtService.generateToken((UserDetails) auth.getPrincipal());
    return ResponseEntity.ok(new AuthResponse(token));
} catch (AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
}
    }


