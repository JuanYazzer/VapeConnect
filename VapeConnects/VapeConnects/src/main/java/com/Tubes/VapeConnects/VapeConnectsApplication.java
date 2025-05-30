package com.Tubes.VapeConnects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.Tubes.VapeConnects.model")
@EnableJpaRepositories("com.Tubes.VapeConnects.repository")
public class VapeConnectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VapeConnectsApplication.class, args);
	}

}
