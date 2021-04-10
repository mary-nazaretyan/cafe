package com.sflpro.cafe.repository;

import java.util.List;
import java.util.Optional;

import com.sflpro.cafe.model.enums.UserRole;
import com.sflpro.cafe.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(UserRole role);
}
