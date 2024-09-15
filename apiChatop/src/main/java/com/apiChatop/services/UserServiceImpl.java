package com.apiChatop.services;

import com.apiChatop.entities.TimestampedEntity;
import com.apiChatop.entities.User;
import com.apiChatop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TimestampedEntity timestampedEntity = new TimestampedEntity();
        timestampedEntity.setCreatedAt(LocalDate.now());
        user.setTimestampedEntity(timestampedEntity);
    userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByEmail(username));
    }

}
