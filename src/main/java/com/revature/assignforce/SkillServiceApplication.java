package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SkillServiceApplication {

	// create a new instance of SpringApplicationBuilder with (SkillServiceApplication.class) as parameters
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(SkillServiceApplication.class).run(args);
	}

}
