package com.swm.ProductOrder;

import com.swm.ProductOrder.controller.ConsoleController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@AllArgsConstructor
public class ProductOrderApplication implements CommandLineRunner {

	@Autowired
	private ConsoleController consoleController;
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// @SpringBootTest 실행 시 console 실행 제어
		if (environment.getActiveProfiles().length == 0
				|| !environment.getActiveProfiles()[0].equals("test")) {
			consoleController.run();
		}
	}
}
