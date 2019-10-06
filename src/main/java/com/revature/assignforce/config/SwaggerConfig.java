package com.revature.assignforce.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.VendorExtension;

/*
 * 
 * SwaggerConfig for creating documentation based on Swagger2, that consist contact information apiInfo 
 *
 */

//Create swagger configuration for JAVA documentation
@Configuration
@EnableSwagger2
// Swagger config for creating JAVA documentation
public class SwaggerConfig {
	
	Contact contact = new Contact("Revature", "http://www.revature.com", "developer@revature.com");
	List<VendorExtension> vendorExtensions = new ArrayList<>();

	ApiInfo apiInfo = new ApiInfo("Photo app RESTful Web Service documentation",
			"This pages documents Photo app RESTful Web Service endpoints", "1.0",
			"http://www.appsdeveloperblof.com/service.html", contact, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
}
