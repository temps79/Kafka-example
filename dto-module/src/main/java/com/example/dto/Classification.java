package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class Classification {
    private String countryName;
    private LocalDate dateOfFoundation;
    private String modelName;

    public Classification(String countryName, LocalDate dateOfFoundation, String modelName) {
        this.countryName = countryName;
        this.dateOfFoundation = dateOfFoundation;
        this.modelName = modelName;
    }

}
