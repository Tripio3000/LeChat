package ru.java.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.api.domain.MessageDto;
import ru.java.api.domain.MessageEntity;
import ru.java.api.domain.MessageMQ;
import ru.java.api.domain.response.MessageResponseDto;
import ru.java.api.filter.MessageFilter;
import ru.java.api.service.MessageService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

//    private final AmqpTemplate template;

    @PostMapping("/generate")
    public ResponseEntity<String> startGeneration(@RequestBody MessageDto inputMessage) {
        System.out.println("Message: " + inputMessage);
        UUID uuid = messageService.saveRequest(inputMessage);
        messageService.sendRequest(uuid, inputMessage);
        
//        UUID randUuid = UUID.randomUUID();

//        Map<UUID, String> messageToSend = messageService.getMessageToSend();
//        MessageMQ mq = new MessageMQ(randUuid, inputMessage.message(), inputMessage.batchSize(), inputMessage.numSteps(), inputMessage.seed());
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        try {
//            String json = ow.writeValueAsString(mq);
//            template.convertAndSend("requestQueue", json);
//            logger.info(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok("Successful");
    }

    @PostMapping("/message-filter")
    public ResponseEntity<List<MessageResponseDto>> getMessagesByFilter(@RequestBody MessageFilter filter) {
        return ResponseEntity.ok(messageService.getMessagesByFilter(filter));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageEntity>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageResponseDto> getMessageById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }
}
