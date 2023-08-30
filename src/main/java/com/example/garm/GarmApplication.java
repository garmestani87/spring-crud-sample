package com.example.garm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class GarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarmApplication.class, args);
	}

}
