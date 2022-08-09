package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@PropertySource({
		"classpath:kafka.properties",
		"classpath:kafka-consumer.properties",
		"classpath:kafka-producer.properties",
})
public class KafkaServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(KafkaServerApplication.class, args);
	}

}
