package com.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ClassificationService {
    void consume(ConsumerRecord<?,String> dto) throws Exception ;
}
