package com.revature.assignforce.messaging.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration 
public class RabbitConfig {
	
	@Bean
	RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		System.out.println("USING RABBIT TEMPLATE CONFIGS");
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			
		});
		return rabbitTemplate;
	}

}