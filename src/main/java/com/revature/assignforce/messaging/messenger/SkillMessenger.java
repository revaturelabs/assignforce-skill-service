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

	@Value("${spring.rabbitmq.skill-routing-delete:assignforce.skill")
	private String routingKey;

	@Value("${spring.rabbitmq.skill-routing-deactivate:assignforce.skill")
	private String deactivateKey;

	@Inject
	public SkillMessenger(RabbitTemplate rabbitTemplate) {
		super();
		logger.info("Setting rabbit template to configured template");
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendCreateMessage(int id){
		logger.info("Sending create message for skill " +id);
		Object message[] = {id, "create"};
		rabbitTemplate.convertAndSend("assignforce", "assignforce.skill", message);
	}

	public void sendDeletionMessage(int id) {
		logger.info("Sending deletion message for skill " + id);
		Object message[] = {id, "delete"};
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}

	public void sendDeactivateMessage(int id) {
		logger.info("Sending deactivation message for skill " + id);
		Object message[] = {id, "deactivate"};
		rabbitTemplate.convertAndSend("assignforce", "assignforce.skill", message);
	}
}
