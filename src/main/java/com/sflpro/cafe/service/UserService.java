package com.sflpro.cafe.service;

import java.util.List;

import com.sflpro.cafe.model.enums.UserRole;
import com.sflpro.cafe.model.jpa.User;
import com.sflpro.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.WAITER);
        userRepository.save(user);
    }

    public List<User> getAllWaiters(){
        return userRepository.findByRole(UserRole.WAITER);
    }

    public User getSignedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository
            .findByUsername(auth.getName())
            .orElse(null);
    }


    public User getUser(String userName) {
        return userRepository
            .findByUsername(userName)
            .orElse(null);
    }
}
