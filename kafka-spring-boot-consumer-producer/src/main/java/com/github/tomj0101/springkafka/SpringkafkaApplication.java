package com.github.tomj0101.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringkafkaApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkaApplication.class, args);
	}
}
