package com.example;

import com.example.dto.Brand;
import com.example.dto.Company;
import com.example.dto.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableKafka
@PropertySource({
		"classpath:kafka.properties",
		"classpath:kafka-consumer.properties",
		"classpath:kafka-producer.properties",
})
public class KafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaClientApplication.class, args);
	}
	@Bean
	public CommandLineRunner initConutries(){
		return args -> {
			Country.USA.setCompanyList(List.of(new Company(Brand.TESLA, LocalDate.of(2003, 7, 1))));
			Country.Germany.setCompanyList(List.of(
					new Company(Brand.AUDI, LocalDate.of(1909, 7, 16)),
					new Company(Brand.BMW, LocalDate.of(1916, 5, 7)))
			);
		};
	}
}
