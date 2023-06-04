//package ru.java.api.rabbitmq;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.java.api.ApiApplication;
//
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//@Component
//@EnableRabbit
//@RequiredArgsConstructor
//public class Listener {
//
//    Logger logger = LoggerFactory.getLogger(Listener.class);
//
//    @RabbitListener(queues = "requestQueue")
//    public void listenQueue(Map<UUID, String> message) {
//        logger.info("Received from requestQueue {}", message);
//    }
//
//}
