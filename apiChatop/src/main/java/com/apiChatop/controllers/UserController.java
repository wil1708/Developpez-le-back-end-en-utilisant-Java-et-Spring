package com.apiChatop.controllers;

import com.apiChatop.dtos.UserDto;
import com.apiChatop.mapper.DtoMapper;
import com.apiChatop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> {
                    UserDto userDto = DtoMapper.INSTANCE.userToUserDto(user);
                    return new ResponseEntity<>(userDto, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/auth/me")
    public ResponseEntity<UserDto> getUserByToken(@RequestHeader("Authorization") String token) {
        try {
            UserDto userDto = userService.findUserByToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
