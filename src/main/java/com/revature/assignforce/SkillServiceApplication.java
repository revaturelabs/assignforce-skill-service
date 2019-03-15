package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSns;

@SpringBootApplication
public class SkillServiceApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(SkillServiceApplication.class).run(args);
	}

}
