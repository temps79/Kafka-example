package com.example.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.car-model}")
    private String carModelTopicName;
    @Value("${kafka.topic.country}")
    private String countryTopicName;

    @Value("${kafka.server}")
    private String kafkaServer;

    @Bean
    public NewTopic carModelTopic() {
        return TopicBuilder.name(carModelTopicName).build();
    }
    @Bean
    public NewTopic countryTopic() {
        return TopicBuilder.name(countryTopicName).build();
    }

    @Bean
    KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new KafkaAdmin(configs);
    }
}
