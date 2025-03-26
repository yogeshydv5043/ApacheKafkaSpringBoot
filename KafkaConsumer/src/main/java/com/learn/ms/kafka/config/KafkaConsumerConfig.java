package com.learn.ms.kafka.config;

import com.learn.ms.kafka.model.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    // 🔹 String Consumer Factory
    @Bean(name = "stringFactory")
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.SERVER_PORT);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.STRING_GROUP_ID);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean(name = "string_kafka_listener")  // ✅ FIXED: Name matches the listener in @KafkaListener
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    // 🔹 Object Consumer Factory
    @Bean(name = "jsonFactory")
    public ConsumerFactory<String, Product> objectConsumerFactory() {
        JsonDeserializer<Product> deserializer = new JsonDeserializer<>(Product.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages(AppConstants.JSON_PACKAGE);
        deserializer.setUseTypeMapperForKey(false);

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.SERVER_PORT);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.JSON_GROUP_ID);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean(name = "json_kafka_listener")  // ✅ FIXED: Name matches the listener in @KafkaListener
    public ConcurrentKafkaListenerContainerFactory<String, Product> objectKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Product> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(objectConsumerFactory());
        return factory;
    }
}
