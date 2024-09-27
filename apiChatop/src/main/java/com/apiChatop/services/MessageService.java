package com.apiChatop.services;

import com.apiChatop.dtos.MessageDto;

public interface MessageService {

    void saveMessage(MessageDto messageDto);
}
