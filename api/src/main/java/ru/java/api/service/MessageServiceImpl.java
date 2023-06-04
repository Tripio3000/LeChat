package ru.java.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import ru.java.api.domain.MessageDto;
import ru.java.api.domain.MessageEntity;
import ru.java.api.domain.MessageMQ;
import ru.java.api.domain.response.MessageResponseDto;
import ru.java.api.exceptions.ModelMapperException;
import ru.java.api.filter.MessageFilter;
import ru.java.api.repository.MessageRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;
    
    private final String PROGRESS = "Initialization";

    private final AmqpTemplate template;

    @Override
    public UUID saveRequest(MessageDto messageDto) {
//        UUID messageId = UUID.randomUUID();
//        logger.info("MessageId: {}", messageId);

        MessageEntity messageEntity = dtoToEntityMapper(messageDto);
        MessageEntity save = messageRepository.save(messageEntity);
        logger.info("Saved to DB");
        return save.getId();

//        Map<UUID, String> mes = new HashMap<>();
//        mes.put(messageId, message);
//        template.convertAndSend("requestQueue", mes);
    }
    
    @Override
    public void sendRequest(UUID uuid, MessageDto messageDto) {
        MessageMQ mq = dtoToMQMapper(uuid, messageDto);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(mq);
            template.convertAndSend("requestQueue", json);
            logger.info(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    public Map<UUID, String> getMessageToSend() {
        MessageFilter filter = MessageFilter.builder().progress("Initialization").build();
        Map<UUID, String> initializingValues = messageRepository.findAll(filter.toSpecification()).stream()
                .collect(Collectors.toMap(MessageEntity::getId, MessageEntity::getMessage));
        return initializingValues;
    }

    public List<MessageResponseDto> getMessagesByFilter(MessageFilter filter) {
        List<MessageEntity> entities = messageRepository.findAll(filter.toSpecification());

        List<MessageResponseDto> result = entities.stream().map(entity -> {
            try {
                return modelMapper.map(entity, MessageResponseDto.class);
            } catch (MappingException e) {
                throw new ModelMapperException("ModelMapper", "Cannot cast MessageEntity to MessageResponseDto");
            }
        }).collect(Collectors.toList());
        logger.info("List messages: {}", result);

        return result;
    }

    public List<MessageEntity> getMessages() {
//        List<MessageEntity> entities = messageRepository.findAll();
        return messageRepository.findAll();
//        entities.stream().map(e -> )
    }

    public MessageResponseDto getMessageById(UUID id) {
        Optional<MessageEntity> entity = messageRepository.findById(id);
        try {
            return modelMapper.map(entity, MessageResponseDto.class);
        } catch (MappingException exception) {
            throw new ModelMapperException("ModelMapper", "Cannot cast MessageEntity to MessageResponseDto");
        }
    }
    
    private MessageEntity dtoToEntityMapper(MessageDto dto) {
        return MessageEntity.builder()
            .message(dto.message())
            .userId(1)
            .progress(PROGRESS)
            .batchSize(dto.batchSize())
            .numSteps(dto.numSteps())
            .seed(dto.seed())
            .build();
    }
    
    private MessageMQ dtoToMQMapper(UUID uuid, MessageDto dto) {
        return MessageMQ.builder()
            .id(uuid)
            .message(dto.message())
            .batch_size(dto.batchSize())
            .num_steps(dto.numSteps())
            .seed(dto.seed())
            .build();
    }
    
    private MessageResponseDto entityToResponseDto(MessageEntity entity) {
        return null;
    }
    

    public void sout() {
        System.out.println("Component");
    }

}
