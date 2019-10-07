package com.revature.assignforce.config;

import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.sns.AmazonSNS;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * Create a template for using with AWS service Amazon SNS
 *
 */
@Configuration
@EnableSwagger2
public class BeansConfig {
	

    @Bean // Create template for using with AmazonSNS (simple notification service)
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }
}
