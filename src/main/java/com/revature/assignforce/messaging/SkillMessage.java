package com.revature.assignforce.messaging;

import com.mangofactory.swagger.plugin.EnableSwagger;

@EnableSwagger
// bean that responsive for comments for skill
public class SkillMessage {
    private int skillId;
    private String context;

        public SkillMessage(){}

        public SkillMessage(int skillId, String context){
            this.skillId = skillId;
            this.context = context;
        }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
