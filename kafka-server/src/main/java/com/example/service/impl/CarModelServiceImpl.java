package com.example.service.impl;

import com.example.dto.CarModel;
import com.example.dto.Classification;
import com.example.service.CarModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    @Value("${kafka.topic.car-model}")
    private String kafkaTopic;

    private final KafkaTemplate<Long, CarModel> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(CarModel dto) {
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send(kafkaTopic, dto);
    }

    private String writeValueAsString(CarModel dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
