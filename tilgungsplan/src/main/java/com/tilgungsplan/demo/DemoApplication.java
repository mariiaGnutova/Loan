package com.tilgungsplan.demo;

import com.tilgungsplan.demo.output.TableGeneration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)  {

		SpringApplication.run(DemoApplication.class, args);

		TableGeneration test = new TableGeneration();
		test.createTable();
	}
}
