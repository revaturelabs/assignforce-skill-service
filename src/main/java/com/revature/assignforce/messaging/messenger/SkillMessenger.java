package com.revature.assignforce.messaging.messenger; 

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class SkillMessenger {

	Logger logger = LoggerFactory.getLogger(SkillMessenger.class);

	private final RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.exchange:assignforce}")
	private String exchange;

	@Value("${spring.rabbitmq.skill-routing-delete:assignforce.skill.delete")
	private String routingKey;

	@Value("${spring.rabbitmq.skill-routing-deactivate:assignforce.skill.deactivate")
	private String deactivateKey;

	@Inject
	public SkillMessenger(RabbitTemplate rabbitTemplate) {
		super();
		logger.info("Setting rabbit template to configured template");
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendDeletionMessage(int id) {
		logger.info("Sending deletion message for skill " + id);
		rabbitTemplate.convertAndSend(exchange, routingKey, id);
	}

	public void sendDeactivateMessage(int id) {
		logger.info("Sending deactivation message for skill " + id);
		rabbitTemplate.convertAndSend("assignforce", "assignforce.skill.deactivate", id);
	}
}
