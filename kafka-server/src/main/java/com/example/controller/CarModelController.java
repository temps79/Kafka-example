package com.example.controller;

import com.example.dto.CarModel;
import com.example.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-model")
@RequiredArgsConstructor
public class CarModelController {
    private final CarModelService service;


    @PostMapping
    public void send(@RequestBody CarModel dto) {
        service.send(dto);
    }
}
