package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.User, Long> {

    Optional<com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.User> findByUsername(String username);

    boolean existsByUsername(String username);
}
