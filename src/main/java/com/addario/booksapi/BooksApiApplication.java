package com.addario.booksapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.*"})
public class BooksApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}

}
