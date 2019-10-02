package com.revature.assignforce.config;

import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.sns.AmazonSNS;
import com.mangofactory.swagger.plugin.EnableSwagger;

@Configuration
@EnableSwagger
public class BeansConfig {

    @Bean // Create template for using with AmazonSNS (simple notification service)
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }
}
