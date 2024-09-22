package com.apiChatop.controllers;

import com.apiChatop.dtos.MessageDto;
import com.apiChatop.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("api/messages/")
    public ResponseEntity<String> createMessage(@RequestHeader("Authorization") String token,
                                                @RequestBody MessageDto messageDto) {
        try {
            messageService.saveMessage(messageDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Message created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating message");
        }
    }

}
