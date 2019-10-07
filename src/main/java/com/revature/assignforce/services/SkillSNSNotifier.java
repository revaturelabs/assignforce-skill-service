package com.revature.assignforce.services;

import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.SkillsNotifierBean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
// INterface that has list of methods responsible for send message
public interface SkillSNSNotifier {
    void sendDeleteNotification(SkillsNotifierBean skillsNotifierBean);
    void sendAddNotification(SkillsNotifierBean skillsNotifierBean);

    default void send(String topic, String subject, SkillsNotifierBean skillsNotifierBean, NotificationMessagingTemplate template) {
        String message = null;
        try {
            message = new ObjectMapper().writeValueAsString(skillsNotifierBean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        template.sendNotification(topic, message, subject);
    }
}
