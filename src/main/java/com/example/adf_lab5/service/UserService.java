package com.example.adf_lab5.service;

import com.example.adf_lab5.entity.MyUser;
import com.example.adf_lab5.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MyUser createUser(String username, String rawPassword, String role, boolean enabled, boolean locked, String firstName, String lastName, String county) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        MyUser user = new MyUser(null, username, hashedPassword, role, enabled, locked, firstName, lastName, county);
        return userRepository.save(user);
    }
}
