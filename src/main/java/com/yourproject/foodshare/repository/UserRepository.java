package com.yourproject.foodshare.repository;

import com.yourproject.foodshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Spring Data JPA automatically creates this query for us
    Optional<User> findByEmail(String email);
}
