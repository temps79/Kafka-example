package com.example.dto;

import java.util.Objects;
import java.util.Optional;

public enum Brand {
    AUDI("Audi"),
    BMW("BMW"),
    TESLA("Tesla");
    private final String value;

    Brand(String value) {
        this.value = value;
    }
    public static Optional<Brand> asString(String value){
        for(Brand brand:Brand.values()){
            if(Objects.equals(brand.value.toLowerCase(), value.toLowerCase())){
                return Optional.of(brand);
            }
        }
        return Optional.empty();
    }
}
