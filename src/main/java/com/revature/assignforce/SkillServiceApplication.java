package com.revature.assignforce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SkillServiceApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(SkillServiceApplication.class).run(args);

//		SpringApplication.run(SkillServiceApplication.class, args);
	}

}
