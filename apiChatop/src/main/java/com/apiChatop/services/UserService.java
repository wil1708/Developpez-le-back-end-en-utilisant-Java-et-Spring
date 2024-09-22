package com.apiChatop.services;

import com.apiChatop.dtos.UserDto;
import com.apiChatop.entities.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService {

     void save(User user);
     Optional<User> findUserById(long id);

     Optional<User> findUserByUsername(String username);

     ArrayList<User> findAllUsers();

     UserDto findUserByToken(String token);
}
