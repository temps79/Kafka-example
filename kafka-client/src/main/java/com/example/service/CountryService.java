package com.example.service;

import com.example.dto.CarModel;
import com.example.dto.Classification;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface CountryService {
    void send(Classification classification);

    void consume(ConsumerRecord<?,String> dto) throws Exception;
}
