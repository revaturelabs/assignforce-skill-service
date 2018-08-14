package com.revature.assignforce.messaging.messenger; 

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class SkillMessenger {

	
	private final RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.exchange:assignforce}")
	private String exchange;

	@Value("${spring.rabbitmq.skill-routing-delete:assignforce.skill.delete")
	private String routingKey;

	@Inject
	public SkillMessenger(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendDeletionMessage(int id) {
		rabbitTemplate.convertAndSend(exchange, routingKey, id);
	}
}
