package com.example.demo.Services;

import com.example.demo.Utilities.CustomUserDetails;
import com.example.demo.model.persistence.AppUser;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser user = userRepo.findByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("User not found when loading by username");
            }
            return new CustomUserDetails(user);
    }

}