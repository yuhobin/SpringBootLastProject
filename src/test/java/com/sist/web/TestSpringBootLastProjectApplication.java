package com.sist.web;

import org.springframework.boot.SpringApplication;

public class TestSpringBootLastProjectApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootLastProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
