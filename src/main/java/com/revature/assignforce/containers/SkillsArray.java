package com.revature.assignforce.containers;

import java.util.List;

import com.revature.assignforce.beans.Skill;

public class SkillsArray {
	List<Skill> skills;

	public SkillsArray() {
		super();
	}

	public SkillsArray(List<Skill> skills) {
		super();
		this.skills = skills;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}	
	
}
