package com.revature.assignforce;

import com.mangofactory.swagger.plugin.EnableSwagger;

@EnableSwagger
public class SkillsNotifierBean {
    private int skillId;

    public SkillsNotifierBean() { // default constructor created by Spring STS  
    }

    public SkillsNotifierBean(int skillId) { // parameterized constructor 
        this.skillId = skillId;
    }

    // Getter and Setter based on variable "skillId"
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
