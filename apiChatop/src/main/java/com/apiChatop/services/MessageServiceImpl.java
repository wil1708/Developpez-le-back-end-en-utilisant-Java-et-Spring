package com.apiChatop.services;

import com.apiChatop.dtos.MessageDto;
import com.apiChatop.entities.Message;
import com.apiChatop.mapper.DtoMapper;
import com.apiChatop.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public void saveMessage(MessageDto messageDto) {
        Message message = DtoMapper.INSTANCE.messageDtoToMessage(messageDto);
        messageRepository.save(message);
    }
}
