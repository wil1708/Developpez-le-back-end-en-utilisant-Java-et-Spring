package com.apiChatop.repositories;

import com.apiChatop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);
}
