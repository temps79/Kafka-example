package com.example.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class CarModel {
    private Brand brand;
    private String modelName;
    private Long travelDistance;
    private Long maxSpeed;

    public void setBrand(String brand) {
        Optional<Brand> optionalBrand=Brand.asString(brand);
        this.brand = optionalBrand.orElse(null);
    }
}
