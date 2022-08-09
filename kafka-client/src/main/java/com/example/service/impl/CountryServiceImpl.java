package com.example.service.impl;

import com.example.dto.*;
import com.example.service.CountryService;
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
public class CountryServiceImpl implements CountryService {

    private final KafkaTemplate<Long, Classification> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.country}")
    private String kafkaTopic;

    @Override
    public void send(Classification classification) {
        log.info("<= sending {}", writeValueAsString(classification));
        kafkaStarshipTemplate.send(kafkaTopic, classification);
    }

    @Override
    @KafkaListener(topics = {"${kafka.topic.car-model}"})
    public void consume(ConsumerRecord<?,String> dto) throws Exception {
        CarModel carModel=readDtoAsString(dto.value());
        Optional<Country> optionalCountry=Country.asBrand(carModel.getBrand());
        if(optionalCountry.isEmpty()) throw new Exception("Country not found");
        Country country=optionalCountry.get();
        Company company=getCompanyAsBrand(country,carModel.getBrand());
        Classification classification=new Classification(country.getName(),company.getDateOfFoundation(),carModel.getModelName());
        send(classification);
    }
    private CarModel readDtoAsString(String dto) {
        try {
            return objectMapper.readValue(dto,CarModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
    private Company getCompanyAsBrand(Country country,Brand brand){
        return country.getCompanyList().stream().filter(company -> Brand.asString(brand.name()).isPresent()).findFirst().orElse(null);
    }
    private String writeValueAsString(Classification classification) {
        try {
            return objectMapper.writeValueAsString(classification);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + classification.toString());
        }
    }
}
