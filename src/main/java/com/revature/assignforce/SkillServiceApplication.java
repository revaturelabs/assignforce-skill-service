package com.revature.assignforce;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableRabbit
@SpringBootApplication
public class SkillServiceApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(SkillServiceApplication.class).run(args);
	}

}
