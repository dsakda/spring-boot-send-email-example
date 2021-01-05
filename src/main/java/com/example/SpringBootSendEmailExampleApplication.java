package com.example;

import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSendEmailExampleApplication implements CommandLineRunner {

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSendEmailExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending email......");
		emailService.sendEmail();
		System.out.println("Send email success");
	}
}
