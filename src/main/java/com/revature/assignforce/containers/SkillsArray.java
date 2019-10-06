package com.revature.assignforce.containers;

import java.util.List;

import com.revature.assignforce.beans.Skill;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * SkillsArray based on List structure, store information about Skills
 *
 */

@EnableSwagger2
// class that response to set skills of batch students for creating portfolio
public class SkillsArray {
	List<Skill> skills;

	// default constructor created by Spring 
	public SkillsArray() {
		super();
	}

	// parameterized constructor 
	public SkillsArray(List<Skill> skills) {
		super();
		this.skills = skills;
	}

	// Getter and Setter
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}	
	
}
