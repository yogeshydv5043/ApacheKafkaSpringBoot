package com.learn.ms.kafka.service;

import com.learn.ms.kafka.config.AppConstant;
import com.learn.ms.kafka.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaService {

    private static final Logger log = LoggerFactory.getLogger(KafkaService.class);
    @Autowired
    private KafkaTemplate<String, Object> jsonTemplate;

    @Autowired
    private KafkaTemplate<String, String> stringTemplate;

    public boolean sendObjectMessage(Product product) {
        product.setId(UUID.randomUUID().toString());
        jsonTemplate.send(AppConstant.OBJECT_TOPIC_NAME, product);
        log.info("Data Sending : {}", product);
        return true;
    }

    public boolean sendStringMessage(String message) {
        if (message != null) {
            message="";
            message = LocalDateTime.now().toString() + " Hello World ";
            stringTemplate.send(AppConstant.STRING_TOPIC_NAME, message);
            log.info("String Data Sending : {}", message);
        } else {
            message = LocalDate.now().toString() + " Hello World";
            stringTemplate.send(AppConstant.STRING_TOPIC_NAME, message);
            log.info("String Sending : {}", message);

        }
        return true;
    }
}
