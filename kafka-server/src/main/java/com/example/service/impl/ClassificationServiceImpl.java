package com.example.service.impl;

import com.example.dto.Classification;
import com.example.service.ClassificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClassificationServiceImpl implements ClassificationService {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = {"${kafka.topic.country}"})
    public void consume(ConsumerRecord<?,String> dto) throws Exception {
        Classification classification=readDtoAsString(dto.value());
        System.out.println(classification);
    }
    private Classification readDtoAsString(String dto) {
        try {
            return objectMapper.readValue(dto,Classification.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
