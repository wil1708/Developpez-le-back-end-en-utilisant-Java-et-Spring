package com.apiChatop.repositories;

import com.apiChatop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String name);
    public User findById(long id);
}
