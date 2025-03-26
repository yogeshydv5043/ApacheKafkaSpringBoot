package com.learn.ms.kafka.config;

import com.learn.ms.kafka.log.Output;
import com.learn.ms.kafka.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumerConfig {

    @Autowired
    private Output output;

    // ✅ FIXED: Using "json_kafka_listener" for Product object messages
    @KafkaListener(topics = AppConstants.OBJECT_TOPIC_NAME, groupId = AppConstants.JSON_GROUP_ID,
            containerFactory = "json_kafka_listener")
    public void resultProduct(Product product) {
        System.out.println("📥 Received Product: " + product.getId());
        output.showResult(product);
    }

    // ✅ FIXED: Using "string_kafka_listener" for String messages
    @KafkaListener(topics = AppConstants.STRING_TOPIC_NAME, groupId = AppConstants.STRING_GROUP_ID,
            containerFactory = "string_kafka_listener")
    public void messageResponse(String message) {
        System.out.println("📥 Received String message: " + message);
    }
}
