package com.tilgungsplan.demo;

import com.tilgungsplan.demo.output.TableGeneration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		SpringApplication.run(DemoApplication.class, args);

		TableGeneration test = new TableGeneration();
		test.createTable();
	}
}
