package io.github.braalex.fpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FplInsightEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FplInsightEngineApplication.class, args);
	}

}
