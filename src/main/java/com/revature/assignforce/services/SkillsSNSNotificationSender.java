package com.revature.assignforce.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.SkillsNotifierBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SkillsSNSNotificationSender implements SkillSNSNotifier {
    @Value("${app.sns.topics.add-skill}")
    private String snsSkillsAddTopic;

    @Value("${app.sns.topics.del-skill}")
    private String snsSkillsDelTopic;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SkillsSNSNotificationSender(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }


    @Override
    public void sendDeleteNotification(SkillsNotifierBean skillsNotifierBean){
        send(snsSkillsDelTopic, "Delete Skill", skillsNotifierBean, notificationMessagingTemplate);
    }

    @Override
    public void sendAddNotification(SkillsNotifierBean skillsNotifierBean){
        send(snsSkillsAddTopic, "Add Skill", skillsNotifierBean, notificationMessagingTemplate);
    }
}
