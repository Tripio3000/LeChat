package ru.java.api.service;

import ru.java.api.domain.MessageDto;
import ru.java.api.domain.MessageEntity;
import ru.java.api.domain.response.MessageResponseDto;
import ru.java.api.filter.MessageFilter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MessageService {

    UUID saveRequest(MessageDto messageDto);
    void sendRequest(UUID uuid, MessageDto messageDto);

    List<MessageResponseDto> getMessagesByFilter(MessageFilter filter);

    Map<UUID, String> getMessageToSend();

    List<MessageEntity> getMessages();

    MessageResponseDto getMessageById(UUID id);

    void sout();
}
