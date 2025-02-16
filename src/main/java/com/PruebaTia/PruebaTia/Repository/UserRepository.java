package com.PruebaTia.PruebaTia.Repository;

import com.PruebaTia.PruebaTia.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByResetCode(String resetCode);
}
