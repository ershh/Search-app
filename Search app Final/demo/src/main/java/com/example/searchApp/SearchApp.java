package com.example.searchApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SearchApp {

	public static void main(String[] args) {
		SpringApplication.run(SearchApp.class, args);
		System.out.println("SearchApp Running..");
	}

	@GetMapping("/status")
	public String deploy() { 
		return "Application deployed to AWS beanstalk.. ";
	}

	@GetMapping
	@RequestMapping("/")
	public String hello() {
		return "Hi! Try searching using: /search/{search value}";
	}

}
