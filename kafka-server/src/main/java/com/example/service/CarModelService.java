package com.example.service;


import com.example.dto.CarModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface CarModelService {
    void send(CarModel dto);

}
