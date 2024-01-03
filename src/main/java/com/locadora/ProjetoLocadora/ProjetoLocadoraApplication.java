package com.locadora.ProjetoLocadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.locadora.ProjetoLocadora.repository")
@SpringBootApplication
public class ProjetoLocadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLocadoraApplication.class, args);
	}

}
