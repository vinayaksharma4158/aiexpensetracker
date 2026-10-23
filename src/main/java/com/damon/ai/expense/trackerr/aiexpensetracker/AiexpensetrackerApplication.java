package com.damon.ai.expense.trackerr.aiexpensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AiexpensetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiexpensetrackerApplication.class, args);
	}

}
