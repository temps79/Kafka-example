package com.example.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Company {
    private Brand brand;
    private LocalDate dateOfFoundation;

    public Company(Brand brand, LocalDate dateOfFoundation) {
        this.brand = brand;
        this.dateOfFoundation = dateOfFoundation;
    }
}
