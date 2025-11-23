package io.github.braalex.fpl;

import org.springframework.boot.SpringApplication;

public class TestFplInsightEngineApplication {

	public static void main(String[] args) {
		SpringApplication.from(FplInsightEngineApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
